package com.limyel.haoyuan.blog.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.common.main.constant.SettingLabelEnum;
import com.limyel.haoyuan.blog.common.project.constant.ProjectErrorMsg;
import com.limyel.haoyuan.blog.common.project.convert.ProjectConvert;
import com.limyel.haoyuan.blog.common.project.dto.project.ProjectDTO;
import com.limyel.haoyuan.blog.common.project.dto.project.ProjectPageDTO;
import com.limyel.haoyuan.blog.common.project.entity.ProjectEntity;
import com.limyel.haoyuan.blog.common.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.blog.common.project.vo.project.ProjectPageVO;
import com.limyel.haoyuan.blog.main.service.SettingService;
import com.limyel.haoyuan.blog.project.dao.ProjectDao;
import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDao projectDao;

    private final ThreadPoolExecutor threadPoolExecutor;

    private final RestTemplate restTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    private final SettingService settingService;

    @Transactional(rollbackFor = Exception.class)
    public int create(ProjectDTO dto) {
        projectDao.validateUnique(null, ProjectEntity::getName, dto.getName(), ProjectErrorMsg.PROJECT_NAME_DUPLICATE);

        ProjectEntity project = ProjectConvert.INSTANCE.toEntity(dto);

        return projectDao.insert(project);
    }

    public int delete(Long id) {
        projectDao.validateExist(id, ProjectErrorMsg.PROJECT_NOT_FOUND);
        return projectDao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(ProjectDTO dto) {
        projectDao.validateExist(dto.getId(), ProjectErrorMsg.PROJECT_NOT_FOUND);
        projectDao.validateUnique(dto.getId(), ProjectEntity::getName, dto.getName(), ProjectErrorMsg.PROJECT_NAME_DUPLICATE);

        ProjectEntity project = ProjectConvert.INSTANCE.toEntity(dto);
        return projectDao.updateById(project);
    }

    public ProjectDTO getById(Long id) {
        ProjectEntity project = projectDao.selectById(id);
        if (project == null) {
            throw new ServiceException(ProjectErrorMsg.PROJECT_NOT_FOUND);
        }

        return ProjectConvert.INSTANCE.toDTO(project);
    }

    public PageData<ProjectPageVO> getPage(ProjectPageDTO dto) {
        Page<ProjectEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<ProjectEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(ProjectEntity::getName, dto.getName());
        wrapperPlus.eqIfPresent(ProjectEntity::getStatus, dto.getStatus());
        wrapperPlus.orderByDesc(ProjectEntity::getCreateTime);

        projectDao.selectPage(page, wrapperPlus);
        List<ProjectPageVO> list = page.getRecords()
                .stream()
                .map(ProjectConvert.INSTANCE::toPageVO)
                .toList();

        return new PageData<>(page, list);
    }

    public List<ProjectListVO> getList() {
        LambdaQueryWrapper<ProjectEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProjectEntity::getStatus, ProjectEntity::getCreateTime);
        List<ProjectEntity> list = projectDao.selectList(wrapper);
        return list
                .stream()
                .map(ProjectConvert.INSTANCE::toListVO)
                .toList();
    }

    @Scheduled(cron = "0 59 23 * * ?")
    public void syncProject() {
        List<ProjectEntity> projects = projectDao.selectList();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + settingService.getValue(SettingLabelEnum.GITHUB_TOKEN.getLabel(), true));
        HttpEntity<Object> request = new HttpEntity<>(headers);

        for (ProjectEntity project : projects) {
            if (StatusEnum.ENABLE.getValue().equals(project.getStatus())) {
                threadPoolExecutor.execute(() -> {
                    String since = LocalDateTime.now().minusDays(1L).format(DateTimeFormatter.ISO_DATE_TIME);
                    ResponseEntity<List> forEntity = restTemplate.exchange("https://api.github.com/repos/" + settingService.getValue(SettingLabelEnum.GITHUB_NAME.getLabel(), true)
                            + "/" + project.getName() + "/commits?sha=master&since=" + since, HttpMethod.GET, request, List.class);
                    log.info("获取项目 {} 的 commit，从 {} 开始共 {} 次。", project.getName(), since, forEntity.getBody().size());
                    int commitNum = 0;
                    for (Object s : forEntity.getBody()) {
                        if (s instanceof Map<?, ?> commit) {
                            Object commitMap = commit.get("commit");
                            if (commitMap instanceof Map<?, ?> map) {
                                Object message = map.get("message");
                                if (message instanceof String msg) {
                                    if (msg.startsWith("feat")) {
                                        commitNum++;
                                    }
                                }
                            }
                        }
                    }

//                    if (commitNum > 0) {
//                        PointChange change = new PointChange();
//                        change.setUserId(1L);
//                        change.setChangedPoint(commitNum * 10L);
//                        change.setType(StatusEnum.ENABLE.getValue());
//                        change.setReason(String.format("项目 %s 提交 %d 次 commit", project.getName(), commitNum));
//
//                        rocketMQTemplate.asyncSend("point-topic", change, new SendCallback() {
//                            @Override
//                            public void onSuccess(SendResult sendResult) {
//                                log.info("消息发送成功: {}", sendResult.getMsgId());
//                            }
//
//                            @Override
//                            public void onException(Throwable throwable) {
//                                throwable.printStackTrace();
//                            }
//                        });
//                    }
                });
            }
        }
    }

}
