package com.limyel.haoyuan.mall.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.rdto.user.PointBalanceRDTO;
import com.limyel.haoyuan.mall.product.api.SkuApi;
import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import com.limyel.haoyuan.mall.trade.constant.OrderRedisKey;
import com.limyel.haoyuan.mall.trade.constant.OrderStatusEnum;
import com.limyel.haoyuan.mall.trade.convert.OrderConvert;
import com.limyel.haoyuan.mall.trade.dao.OrderDao;
import com.limyel.haoyuan.mall.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.mall.trade.dto.order.OrderItemDTO;
import com.limyel.haoyuan.mall.trade.dto.order.OrderPayDTO;
import com.limyel.haoyuan.mall.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.mall.trade.entity.OrderEntity;
import com.limyel.haoyuan.mall.trade.vo.order.OrderConfirmVO;
import com.limyel.haoyuan.mall.trade.vo.order.OrderListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    private final OrderItemService orderItemService;

    private final UserProductService userProductService;

    private final SkuApi skuApi;

    private final UserApi userApi;

    private final StringRedisTemplate redisTemplate;

    public PageData<OrderListVO> getList(PageParam pageParam) {
        Page<OrderEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        Long loginId = (Long) StpUserUtil.getLoginId();
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

        List<StockDeductRDTO.SkuDTO> deductSpuList = new ArrayList<>();
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
            StockDeductRDTO.SkuDTO deductSpu = new StockDeductRDTO.SkuDTO();
            deductSpu.setSkuId(spu.getId());
            deductSpu.setQuantity(orderItem.getQuantity());
            deductSpuList.add(deductSpu);

            totalAmount += orderItem.getQuantity() * spu.getPrice();
            totalQuantity += orderItem.getQuantity();
        }

        // 校验库存并锁定库存？
        // 扣减库存
        StockDeductRDTO deductDTO = new StockDeductRDTO();
        deductDTO.setOrderToken(orderToken);
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
        order.setUserId(StpUserUtil.getLoginIdAsLong());
        orderDao.insert(order);
        orderItemService.create(order.getId(), dto.getOrderItems());

        return order.getOrderSn();
    }

    public void pay(OrderPayDTO dto) {
        String orderSn = dto.getOrderSn();
        OrderEntity order = orderDao.selectOne(OrderEntity::getOrderSn, orderSn);
        Assert.notNull(order, "订单不存在");

        Assert.isTrue(OrderStatusEnum.UNPAID.getValue().equals(order.getStatus()), "订单不可支付，请检查订单状态");

        PointBalanceRDTO pointBalanceRDTO = new PointBalanceRDTO();
        pointBalanceRDTO.setUserId(StpUserUtil.getLoginIdAsLong());
        pointBalanceRDTO.setType(dto.getPaymentMethod());
        pointBalanceRDTO.setTotal(order.getPaymentAmount());
        if (!userApi.deductPointBalance(pointBalanceRDTO)) {
            throw new ServiceException("支付失败");
        }
        order.setStatus(OrderStatusEnum.COMPLETE.getValue());
        orderDao.updateById(order);

        userProductService.createOrUpdate(StpUserUtil.getLoginIdAsLong(), order.getId());
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
