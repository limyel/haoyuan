package com.limyel.haoyuan.blog.common.project.vo.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectListVO {

    private String name;

    private String summary;

    private String url;

    private Integer status;

    private LocalDateTime lastCommitTime;

}
