package com.limyel.haoyuan.blog.sync.event.subscriber;

import com.limyel.haoyuan.blog.sync.convert.RecordConvert;
import com.limyel.haoyuan.blog.sync.dao.RecordDao;
import com.limyel.haoyuan.blog.sync.dto.PointDTO;
import com.limyel.haoyuan.blog.sync.entity.RecordEntity;
import com.limyel.haoyuan.blog.sync.event.PointAddEvent;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointAddSubscriber implements ApplicationListener<PointAddEvent> {

    private final RecordDao recordDao;

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public void onApplicationEvent(PointAddEvent event) {
        RecordEntity record = new RecordEntity();
        BeanUtils.copyProperties(event, record);
        record.setStatus(StatusEnum.DISABLE.getValue());
        recordDao.insert(record);

        PointDTO pointDTO = RecordConvert.INSTANCE.toPointDTO(record);
        rocketMQTemplate.asyncSend("point-topic", pointDTO, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("消息发送成功: {}", sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }
}
