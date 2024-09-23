package com.limyel.haoyuan.blog.common.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.IBaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("project_commit_log")
public class CommitLogEntity implements IBaseEntity {

    private Long id;

    private Long projectId;

    private Integer commitNum;

    private LocalDateTime commitTime;

    private LocalDateTime createTime;

}
