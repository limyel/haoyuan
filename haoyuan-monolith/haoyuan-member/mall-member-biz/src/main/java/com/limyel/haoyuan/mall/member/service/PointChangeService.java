package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.mall.common.member.dto.user.api.PointBalanceChange;
import com.limyel.haoyuan.mall.common.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "point-topic", consumerGroup = "point-consumer")
public class PointChangeService implements RocketMQListener<PointBalanceChange> {

    private final PayLogService payLogService;

    private final UserService userService;

    @Override
    public void onMessage(PointBalanceChange dto) {
        UserEntity user = userService.getById(dto.getUserId());
        // todo
    }
}
