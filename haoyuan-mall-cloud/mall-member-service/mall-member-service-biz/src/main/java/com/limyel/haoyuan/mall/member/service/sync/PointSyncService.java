package com.limyel.haoyuan.mall.member.service.sync;

import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointSyncService {

    private final RestTemplate restTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    public void syncPost() {


        PayLogEntity payLog = new PayLogEntity();

        rocketMQTemplate.asyncSend("point-topic", payLog, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("消息发送成功: {}", sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    public void syncProject() {

    }

}
