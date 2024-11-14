package com.limyel.blog.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListDTO {

    private Integer year = LocalDateTime.now().getYear();

    private String tag;

}
