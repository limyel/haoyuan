package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.common.member.dto.user.api.PointBalanceChange;
import com.limyel.haoyuan.mall.common.product.dto.api.SkuConfirm;
import com.limyel.haoyuan.mall.common.product.dto.api.StockDeduct;
import com.limyel.haoyuan.mall.common.trade.constant.OrderRedisKey;
import com.limyel.haoyuan.mall.common.trade.constant.OrderStatusEnum;
import com.limyel.haoyuan.mall.common.trade.convert.OrderConvert;
import com.limyel.haoyuan.mall.common.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.mall.common.trade.dto.order.OrderItemDTO;
import com.limyel.haoyuan.mall.common.trade.dto.order.OrderPayDTO;
import com.limyel.haoyuan.mall.common.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.mall.common.trade.entity.OrderEntity;
import com.limyel.haoyuan.mall.common.trade.vo.order.OrderConfirmVO;
import com.limyel.haoyuan.mall.common.trade.vo.order.OrderListVO;
import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.product.api.SkuApi;
import com.limyel.haoyuan.mall.trade.dao.OrderDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;

    private final OrderItemService orderItemService;

    private final UserProductService userProductService;

    private final SkuApi skuApi;

    private final UserApi userApi;

    private final StringRedisTemplate redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    public PageData<OrderListVO> getList(PageParam pageParam) {
        // todo

        Page<OrderEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderEntity::getUserId, null);

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

    /**
     * 确认订单，返回订单 token 和订单详情
     * @param dto
     * @return
     */
    public OrderConfirmVO confirm(OrderConfirmDTO dto) {
        OrderConfirmVO result = new OrderConfirmVO();

        // 获取订单商品
        for (OrderConfirmDTO.CartItem item : dto.getList()) {
            SkuConfirm sku = skuApi.getById(item.getSkuId());
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

    /**
     * 提交订单，返回订单编号
     * @param dto
     * @return
     */
    public String submit(OrderSubmitDTO dto) {
        dto.setOrderSn(UUID.randomUUID().toString().replace("-", ""));

        // todo

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
        List<SkuConfirm> spuList = skuApi.getByIds(skuIds);

        List<StockDeduct.SkuDTO> deductSpuList = new ArrayList<>();
        Long totalAmount = 0L;
        Integer totalQuantity = 0;

        for (OrderItemDTO orderItem : orderItems) {
            SkuConfirm spu = spuList.stream()
                    .filter(item -> item.getId().equals(orderItem.getSkuId()))
                    .findFirst()
                    .orElse(null);
            Assert.isTrue(spu != null, "商品已下架或删除");
            Assert.isTrue(orderItem.getPrice().compareTo(spu.getPrice()) == 0, "商品价格发生变动，请刷新页面");

            // 用于扣减库存
            StockDeduct.SkuDTO deductSku = new StockDeduct.SkuDTO();
            deductSku.setSkuId(spu.getId());
            deductSku.setQuantity(orderItem.getQuantity());
            deductSpuList.add(deductSku);

            totalAmount += orderItem.getQuantity() * spu.getPrice();
            totalQuantity += orderItem.getQuantity();
        }

        // 校验库存并锁定库存？
        // 扣减库存
        StockDeduct deductDTO = new StockDeduct();
        deductDTO.setOrderSn(dto.getOrderSn());
        deductDTO.setSkuList(deductSpuList);
        skuApi.deduct(deductDTO);

        // 创建订单实例
        OrderEntity order = new OrderEntity();
        order.setOrderSn(UUID.randomUUID().toString().replace("-", ""));
        order.setRemark(dto.getRemark());
        order.setStatus(OrderStatusEnum.UNPAID.getValue());
        order.setTotalAmount(totalAmount);
        order.setTotalQuantity(totalQuantity);
        order.setPaymentAmount(totalAmount);
        order.setUserId(null);
        orderDao.insert(order);
        orderItemService.create(order.getId(), dto.getOrderItems());

        long ts = System.currentTimeMillis() + 5000;
        rocketMQTemplate.asyncSend("order-pay-topic", order, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("订单 {} 支付延时消息发送成功", order.getOrderSn());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("订单 {} 支付延时消息发送失败，原因：{}", order.getOrderSn(), throwable.getMessage());
            }
        }, ts);
        return order.getOrderSn();
    }

    public void pay(OrderPayDTO dto) {
        // todo

        String orderSn = dto.getOrderSn();
        OrderEntity order = orderDao.selectOne(OrderEntity::getOrderSn, orderSn);
        Assert.notNull(order, "订单不存在");

        Assert.isTrue(OrderStatusEnum.UNPAID.getValue().equals(order.getStatus()), "订单不可支付，请检查订单状态");

        PointBalanceChange pointBalance = new PointBalanceChange();
        pointBalance.setUserId(null);
        pointBalance.setType(dto.getPaymentMethod());
        pointBalance.setTotal(order.getPaymentAmount());
        // todo
//        if (!userApi.deductPointBalance(pointBalance)) {
//            throw new ServiceException("支付失败");
//        }
        order.setStatus(OrderStatusEnum.COMPLETE.getValue());
        orderDao.updateById(order);

        // todo
        userProductService.createOrUpdate(null, order.getId());
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


}
