package com.limyel.blog.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleVO {

    private String title;

    private String content;

    private List<TagListVO> tags;

    private String createTime;

    private String updateTime;

}
