package com.limyel.haoyuan.blog.project.service;

import com.limyel.haoyuan.blog.project.config.properties.GithubProperties;
import com.limyel.haoyuan.blog.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class ProjectSyncService {

    private final ThreadPoolExecutor threadPoolExecutor;

    private final ProjectService projectService;

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    /**
     * 同步 GitHub 的提交信息
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void sync() {
        List<ProjectListVO> projects = projectService.getList();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + githubProperties.getToken());
        HttpEntity<Object> request = new HttpEntity<>(headers);

        for (ProjectListVO project : projects) {
            threadPoolExecutor.execute(() -> {
                String since = LocalDateTime.now().minusDays(1L).format(DateTimeFormatter.ISO_DATE_TIME);
                ResponseEntity<List> forEntity = restTemplate.exchange("https://api.github.com/repos/" + githubProperties.getUsername() + "/" + project.getName() + "/commits?sha=master&since=" + since, HttpMethod.GET, request, List.class);
                log.info("获取项目 {} 的 commit，从 {} 开始共 {} 条。", project.getName(), since, forEntity.getBody().size());
                for (Object s : forEntity.getBody()) {
                    String commit = String.valueOf(s);
                    Map<String, Object> commitMap = JSONUtil.parseObject(commit);
                    Object message = commitMap.get("message");
                    if (message instanceof String msg) {
                        if (msg.startsWith("feat")) {
//                            recordService.create(10L, "项目推送了一个 commit", "limyel");
                        }
                    }
                }
            });
        }
    }

}
