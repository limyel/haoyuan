package com.limyel.haoyuan.mall.member.service.sync;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.mall.member.entity.PayLogEntity;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointSyncService {

    private final RestTemplate restTemplate;

    private final UserService userService;

    private final RocketMQTemplate rocketMQTemplate;

    private final ThreadPoolExecutor threadPoolExecutor;

    @Value("${haoyuan.github.token:}")
    private String token;
    @Value("${haoyuan.github.username:}")
    private String username;

    @Scheduled(cron = "0 59 23 * * ?")
    public void syncPost() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.limyel.com/blog/app/post/get/today-num", String.class);
        String body = responseEntity.getBody();
        Map<String, Object> bodyMap = JSONUtil.parseObject(body);
        Object data = bodyMap.get("data");
        if (data != null) {
            Long num = Long.valueOf((String) data);
            if (num == 0) {
                return;
            }
            PayLogEntity payLog = new PayLogEntity();
            payLog.setChangedPoint(num * 100);
            payLog.setType(StatusEnum.ENABLE.getValue());
            payLog.setReason(String.format("发布 %d 篇文章", num));
            payLog.setUserId(userService.getByBlogUsername("limyel").getId());

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
    }

//    @Scheduled(cron = "0 59 23 * * ?")
    @Scheduled(cron = "5 * * * * ?")
    public void syncProject() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.limyel.com/blog/app/project/get/list", String.class);
        String body = responseEntity.getBody();
        Map<String, Object> bodyMap = JSONUtil.parseObject(body);
        Object data = bodyMap.get("data");
        if (data != null && data instanceof List<?> list) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "token " + token);
            HttpEntity<Object> request = new HttpEntity<>(headers);

            for (Object project : list) {
                if (project instanceof Map<?,?> projectMap) {
                    Object projectName = projectMap.get("name");
                    if (projectName != null) {
                        threadPoolExecutor.execute(() -> {
                            String since = LocalDateTime.now().minusDays(1L).format(DateTimeFormatter.ISO_DATE_TIME);
                            ResponseEntity<List> forEntity = restTemplate.exchange("https://api.github.com/repos/" + username + "/" + projectName + "/commits?sha=master&since=" + since, HttpMethod.GET, request, List.class);
                            log.info("获取项目 {} 的 commit，从 {} 开始共 {} 次。", projectName, since, forEntity.getBody().size());
                            int commitNum = 0;
                            for (Object s : forEntity.getBody()) {
                                if (s instanceof Map<?,?> commit) {
                                    Object commitMap = commit.get("commit");
                                    if (commitMap instanceof Map<?,?> map) {
                                        Object message = map.get("message");
                                        if (message instanceof String msg) {
                                            if (msg.startsWith("feat")) {
                                                commitNum++;
                                            }
                                        }
                                    }
                                }
                            }

                            if (commitNum > 0) {
                                PayLogEntity payLog = new PayLogEntity();
                                payLog.setChangedPoint(commitNum * 10L);
                                payLog.setType(StatusEnum.ENABLE.getValue());
                                payLog.setReason(String.format("项目 %s 提交 %d 次 commit", projectName, commitNum));
                                payLog.setUserId(userService.getByBlogUsername("limyel").getId());

                                rocketMQTemplate.asyncSend("point-topic", payLog, new SendCallback() {
                                    @Override
                                    public void onSuccess(SendResult sendResult) {
                                        log.info("消息发送成功: {}", sendResult.getMsgId());
                                    }

                                    @Override
                                    public void onException(Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                });
                            }
                        });
                    }
                }
            }
        }
    }

}
