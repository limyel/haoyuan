package com.limyel.haoyuan.blog.content.service;

import com.limyel.haoyuan.blog.content.dto.post.PostPublishDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-sender", consumerGroup = "myconsumer")
public class PostPublishService implements RocketMQListener<PostPublishDTO> {

    @Override
    public void onMessage(PostPublishDTO dto) {
        log.info("收到消息: {}", dto.getId());
    }
}
