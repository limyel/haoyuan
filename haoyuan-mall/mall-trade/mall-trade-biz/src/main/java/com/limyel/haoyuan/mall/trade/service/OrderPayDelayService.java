package com.limyel.haoyuan.mall.trade.service;

import com.limyel.haoyuan.mall.trade.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "order-pay-topic", consumerGroup = "myconsumer")
public class OrderPayDelayService implements RocketMQListener<OrderEntity> {

    private final OrderService orderService;

    @Override
    public void onMessage(OrderEntity orderEntity) {
        System.out.println(orderEntity.getOrderSn());
    }
}
