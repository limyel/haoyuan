package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.mall.member.convert.PayLogConvert;
import com.limyel.haoyuan.mall.member.dto.pointlog.PointChange;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "point-topic", consumerGroup = "point-consumer")
public class PointChangeService implements RocketMQListener<PointChange> {

    private final PayLogService payLogService;

    private final UserService userService;

    @Override
    public void onMessage(PointChange change) {
        UserEntity user = userService.getById(change.getUserId());
        // todo
        if (StatusEnum.ENABLE.getValue().equals(change.getType())) {
            user.setPoint(user.getPoint() + change.getChangedPoint());
        }
        userService.update(user);

        PayLogEntity payLog = PayLogConvert.INSTANCE.toEntity(change);
        payLogService.create(payLog);
    }
}
