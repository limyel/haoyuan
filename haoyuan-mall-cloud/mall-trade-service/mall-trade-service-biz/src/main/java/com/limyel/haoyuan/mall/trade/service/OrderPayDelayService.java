package com.limyel.haoyuan.mall.trade.service;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "order-pay-topic", consumerGroup = "myconsumer")
public class OrderPayDelayService implements RocketMQListener<String> {

    private final OrderService orderService;

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
