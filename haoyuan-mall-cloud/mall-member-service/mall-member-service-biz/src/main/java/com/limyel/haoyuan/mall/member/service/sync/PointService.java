package com.limyel.haoyuan.mall.member.service.sync;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.mall.common.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.common.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.service.PayLogService;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "point-topic", consumerGroup = "point-consumer")
public class PointService implements RocketMQListener<PayLogEntity> {

    private final PayLogService payLogService;

    private final UserService userService;

    @Override
    public void onMessage(PayLogEntity payLog) {
        UserEntity user = userService.getById(payLog.getUserId());
        if (StatusEnum.ENABLE.getValue().equals(payLog.getType())) {
            user.setPoint(user.getPoint() + payLog.getChangedPoint());
        }
        userService.update(user);

        payLogService.create(payLog);
    }
}
