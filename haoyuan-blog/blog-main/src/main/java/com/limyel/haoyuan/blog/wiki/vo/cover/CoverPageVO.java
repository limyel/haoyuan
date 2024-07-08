package com.limyel.haoyuan.blog.wiki.vo.cover;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoverPageVO {

    private Long id;

    private String title;

    private Integer status;

    private LocalDateTime createTime;

}
