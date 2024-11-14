package com.limyel.blog.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO {

    private Long id;

    private String title;

    private String slug;

    private String content;

    private Boolean top;

    private Integer status;

    private List<Long> tags;

}
