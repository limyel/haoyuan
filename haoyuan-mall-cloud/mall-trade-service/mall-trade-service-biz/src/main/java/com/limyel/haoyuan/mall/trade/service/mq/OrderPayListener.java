package com.limyel.haoyuan.mall.trade.service.mq;

import com.limyel.haoyuan.mall.trade.constant.OrderStatusEnum;
import com.limyel.haoyuan.mall.trade.entity.OrderEntity;
import com.limyel.haoyuan.mall.trade.entity.OrderItemEntity;
import com.limyel.haoyuan.mall.trade.service.OrderItemService;
import com.limyel.haoyuan.mall.trade.service.OrderService;
import com.limyel.haoyuan.mallcloud.product.api.SkuApi;
import com.limyel.haoyuan.mallcloud.product.dto.StockReturn;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "order-pay-topic", consumerGroup = "myconsumer")
public class OrderPayListener implements RocketMQListener<String> {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final SkuApi skuApi;

    @Override
    public void onMessage(String orderSn) {
        OrderEntity order = orderService.getBySn(orderSn);
        if (OrderStatusEnum.UNPAID.getValue().equals(order.getStatus())) {
            order.setStatus(OrderStatusEnum.TIMEOUT.getValue());
            orderService.update(order);

            List<OrderItemEntity> orderItems = orderItemService.getByOrderId(order.getId());
            List<StockReturn.Sku> list = orderItems.stream()
                    .map(orderItem -> {
                        StockReturn.Sku sku = new StockReturn.Sku();
                        BeanUtils.copyProperties(orderItem, sku);
                        return sku;
                    }).toList();
            StockReturn dto = new StockReturn();
            dto.setList(list);
            skuApi.returnStock(dto);
        }
    }
}
