package com.limyel.haoyuan.blog.project.vo.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectPageVO {

    private Long id;

    private String name;

    private String url;

    private LocalDateTime createTime;

}
