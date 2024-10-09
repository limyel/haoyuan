package com.limyel.haoyuan.cloud.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.cloud.mall.trade.constant.OrderRedisKey;
import com.limyel.haoyuan.cloud.mall.trade.constant.OrderStatusEnum;
import com.limyel.haoyuan.cloud.mall.trade.convert.OrderConvert;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderItemDTO;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderPayDTO;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.cloud.mall.trade.entity.OrderEntity;
import com.limyel.haoyuan.cloud.mall.trade.vo.order.OrderConfirmVO;
import com.limyel.haoyuan.cloud.mall.trade.vo.order.OrderListVO;
import com.limyel.haoyuan.cloud.member.api.UserFeignClient;
import com.limyel.haoyuan.cloud.member.dto.PointBalanceChange;
import com.limyel.haoyuan.cloud.security.util.SecurityUtil;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.cloud.mall.trade.dao.OrderDao;
import com.limyel.haoyuan.cloud.mall.product.api.SkuFeignClient;
import com.limyel.haoyuan.cloud.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.cloud.mall.product.dto.StockDeduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;

    private final OrderItemService orderItemService;

    private final UserProductService userProductService;

    private final SkuFeignClient skuFeignClient;

    private final UserFeignClient userFeignClient;

    private final StringRedisTemplate redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    public int update(OrderEntity order) {
        return orderDao.updateById(order);
    }

    @Transactional(rollbackFor = Exception.class)
    public int create(OrderSubmitDTO dto) {
        OrderEntity order = new OrderEntity();

        order.setOrderSn(dto.getOrderSn());
        order.setRemark(dto.getRemark());
        order.setStatus(OrderStatusEnum.UNPAID.getValue());
        order.setUserId(SecurityUtil.getMemberUserId().get());
        buildOrder(order, dto.getOrderItems());
        int result = orderDao.insert(order);

        orderItemService.create(order.getId(), dto.getOrderItems());

        return result;
    }

    public PageData<OrderListVO> getList(PageParam pageParam) {
        Page<OrderEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        Long loginId = SecurityUtil.getMemberUserId().get();
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderEntity::getUserId, loginId);

        orderDao.selectPage(page, wrapper);

        List<OrderListVO> list = page.getRecords().stream()
                .map(item -> {
                    OrderListVO result = OrderConvert.INSTANCE.toListVO(item);
                    result.setOrderItems(orderItemService.getList(result.getId()));
                    return result;
                })
                .toList();
        return new PageData<>(page, list);
    }

    public OrderEntity getBySn(String orderSn) {
        OrderEntity result = orderDao.selectOne(OrderEntity::getOrderSn, orderSn);
        if (result == null) {
            throw new ServiceException("订单不存在");
        }
        return result;
    }

    /**
     * 确认订单，返回订单 token 和订单详情
     *
     * @param dto
     * @return
     */
    public OrderConfirmVO confirm(OrderConfirmDTO dto) {
        OrderConfirmVO result = new OrderConfirmVO();

        // 获取订单商品
        for (OrderConfirmDTO.CartItem item : dto.getList()) {
            SkuConfirm sku = skuFeignClient.getById(item.getSkuId());
            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setSpuName(sku.getSpuName());
            orderItem.setSkuId(sku.getId());
            orderItem.setSkuName(sku.getName());
            orderItem.setPrice(sku.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setType(sku.getType());

            result.getOrderItems().add(orderItem);
        }

        // 创建 orderToken，避免重复支付
        String orderToken = UUID.randomUUID().toString().replace("-", "");
        result.setOrderToken(orderToken);
        redisTemplate.opsForValue().set(OrderRedisKey.ORDER_TOKEN_PREFIX + orderToken, orderToken);

        return result;
    }

    public String submitOrderSn(OrderSubmitDTO dto) {
        checkDuplicateOrder(dto.getOrderToken());

        dto.setOrderSn(UUID.randomUUID().toString().replace("-", ""));
        redisTemplate.opsForValue().set("ORDER:SUBMIT:" + dto.getOrderSn(), JSONUtil.toJson(dto), 30, TimeUnit.MINUTES);
        rocketMQTemplate.sendMessageInTransaction("submit-order-tx", MessageBuilder.withPayload(dto.getOrderSn()).build()
                , dto.getOrderSn());
        return dto.getOrderSn();
    }

    /**
     * 判断订单是否重复提交
     */
    private void checkDuplicateOrder(String orderToken) {
        // todo 用 lua 脚本保证原子性
        String key = OrderRedisKey.ORDER_TOKEN_PREFIX + orderToken;
        String token = redisTemplate.opsForValue().get(key);
        if (!StringUtils.hasText(token)) {
            throw new ServiceException("订单重复提交");
        }
        redisTemplate.delete(key);
    }

    /**
     * 订单商品校验，确认商品价格、状态
     *
     * @param orderItems
     */
    private void checkSku(List<OrderItemDTO> orderItems) {
        List<Long> skuIds = orderItems.stream()
                .map(OrderItemDTO::getSkuId)
                .toList();
        List<SkuConfirm> spuList = skuFeignClient.getByIds(skuIds);
        for (OrderItemDTO orderItem : orderItems) {
            SkuConfirm spu = spuList.stream()
                    .filter(item -> item.getId().equals(orderItem.getSkuId()))
                    .findFirst()
                    .orElse(null);
            Assert.isTrue(spu != null, "商品已下架或删除");
            Assert.isTrue(orderItem.getPrice().compareTo(spu.getPrice()) == 0, "商品价格发生变动，请刷新页面");
        }
    }

    /**
     * 扣减库存
     *
     * @param orderItems
     * @param orderSn
     * @return
     */
    public void deductStock(List<OrderItemDTO> orderItems, String orderSn) {
        checkSku(orderItems);

        List<StockDeduct.SkuDTO> deductSpuList = new ArrayList<>();

        for (OrderItemDTO orderItem : orderItems) {
            // 用于扣减库存
            StockDeduct.SkuDTO deductSpu = new StockDeduct.SkuDTO();
            deductSpu.setSkuId(orderItem.getSkuId());
            deductSpu.setQuantity(orderItem.getQuantity());
            deductSpuList.add(deductSpu);
        }

        // 校验库存并锁定库存？
        // 扣减库存
        StockDeduct deductDTO = new StockDeduct();
        deductDTO.setOrderSn(orderSn);
        deductDTO.setSkuList(deductSpuList);
        skuFeignClient.deductStock(deductDTO);
    }

    /**
     * 提交订单，返回订单编号
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String submit(OrderSubmitDTO dto) {
        dto.setOrderSn(UUID.randomUUID().toString().replace("-", ""));

        // 判断订单是否重复提交
        // todo 用 lua 脚本保证原子性
        String orderToken = dto.getOrderToken();
        String key = OrderRedisKey.ORDER_TOKEN_PREFIX + orderToken;
        String token = redisTemplate.opsForValue().get(key);
        if (!StringUtils.hasText(token)) {
            throw new ServiceException("订单重复提交");
        }
        redisTemplate.delete(key);

        // 订单商品校验，确认商品价格、状态
        List<OrderItemDTO> orderItems = dto.getOrderItems();
        List<Long> skuIds = orderItems.stream()
                .map(OrderItemDTO::getSkuId)
                .toList();
        List<SkuConfirm> spuList = skuFeignClient.getByIds(skuIds);

        List<StockDeduct.SkuDTO> deductSpuList = new ArrayList<>();
        Long totalAmount = 0L;
        Integer totalQuantity = 0;

        for (OrderItemDTO orderItem : orderItems) {
            SkuConfirm sku = spuList.stream()
                    .filter(item -> item.getId().equals(orderItem.getSkuId()))
                    .findFirst()
                    .orElse(null);
            Assert.isTrue(sku != null, "商品已下架或删除");
            Assert.isTrue(orderItem.getPrice().compareTo(sku.getPrice()) == 0, "商品价格发生变动，请刷新页面");

            // 用于扣减库存
            StockDeduct.SkuDTO deductSpu = new StockDeduct.SkuDTO();
            deductSpu.setSkuId(sku.getId());
            deductSpu.setQuantity(orderItem.getQuantity());
            deductSpuList.add(deductSpu);

            totalAmount += orderItem.getQuantity() * sku.getPrice();
            totalQuantity += orderItem.getQuantity();
        }

        // 校验库存并锁定库存？
        // 扣减库存
        StockDeduct deductDTO = new StockDeduct();
        deductDTO.setOrderSn(dto.getOrderSn());
        deductDTO.setSkuList(deductSpuList);
        skuFeignClient.deductStock(deductDTO);

        // 创建订单实例
        OrderEntity order = new OrderEntity();
        order.setOrderSn(dto.getOrderSn());
        order.setRemark(dto.getRemark());
        order.setStatus(OrderStatusEnum.UNPAID.getValue());
        order.setTotalAmount(totalAmount);
        order.setTotalQuantity(totalQuantity);
        order.setPaymentAmount(totalAmount);
        order.setUserId(SecurityUtil.getMemberUserId().get());
        orderDao.insert(order);
        orderItemService.create(order.getId(), dto.getOrderItems());

        Message<String> orderDelayMsg = MessageBuilder.withPayload(order.getOrderSn()).build();
        rocketMQTemplate.asyncSend("order-pay-topic", orderDelayMsg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("订单 {} 支付延时消息发送成功", order.getOrderSn());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("订单 {} 支付延时消息发送失败，原因：{}", order.getOrderSn(), throwable.getMessage());
            }
        }, 3000, 3);

        return order.getOrderSn();
    }

    public void pay(OrderPayDTO dto) {
        String orderSn = dto.getOrderSn();
        OrderEntity order = null;
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order = orderDao.selectOne(OrderEntity::getOrderSn, orderSn);
            if (order != null) {
                break;
            }
        }

        Assert.notNull(order, "订单创建失败");

        Assert.isTrue(OrderStatusEnum.UNPAID.getValue().equals(order.getStatus()), "订单不可支付，请检查订单状态");

        PointBalanceChange pointBalanceChange = new PointBalanceChange();
        pointBalanceChange.setUserId(SecurityUtil.getMemberUserId().get());
        pointBalanceChange.setType(dto.getPaymentMethod());
        pointBalanceChange.setTotal(order.getPaymentAmount());
        if (!userFeignClient.deductPointBalance(pointBalanceChange)) {
            throw new ServiceException("支付失败");
        }
        order.setStatus(OrderStatusEnum.COMPLETE.getValue());
        orderDao.updateById(order);

        userProductService.createOrUpdate(SecurityUtil.getMemberUserId().get(), order.getId());
    }

    public void cancel(String orderSn) {
        int result = orderDao.update(new LambdaUpdateWrapper<OrderEntity>()
                .eq(OrderEntity::getOrderSn, orderSn)
                .eq(OrderEntity::getStatus, OrderStatusEnum.UNPAID.getValue())
                .set(OrderEntity::getStatus, OrderStatusEnum.CANCELED.getValue()));
        if (result != 1) {
            throw new ServiceException("订单取消失败");
        }
    }

    /**
     * 补充订单的信息，总额、总量等
     *
     * @param order
     * @param orderItems
     */
    private void buildOrder(OrderEntity order, List<OrderItemDTO> orderItems) {
        List<StockDeduct.SkuDTO> deductSpuList = new ArrayList<>();
        Long totalAmount = 0L;
        Integer totalQuantity = 0;

        for (OrderItemDTO orderItem : orderItems) {
            totalAmount += orderItem.getQuantity() * orderItem.getPrice();
            totalQuantity += orderItem.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        order.setPaymentAmount(totalAmount);
        order.setTotalQuantity(totalQuantity);
    }

}
