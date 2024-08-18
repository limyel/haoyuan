package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.mall.member.dto.paylog.PointDTO;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "point-topic", consumerGroup = "myconsumer")
public class PointService implements RocketMQListener<PointDTO> {

    private final PayLogService payLogService;

    private final UserService userService;

    @Override
    public void onMessage(PointDTO dto) {
        UserEntity user = userService.getByBlogUsername(dto.getUsername());
        PayLogEntity payLog = new PayLogEntity();
        payLog.setUserId(user.getId());
        payLog.setType(StatusEnum.ENABLE.getValue());
        payLog.setReason(dto.getReason());
        payLog.setChangedPoint(dto.getPoint());
        payLogService.create(payLog);

        user.setPoint(user.getPoint() + dto.getPoint());
        userService.update(user);
    }
}
