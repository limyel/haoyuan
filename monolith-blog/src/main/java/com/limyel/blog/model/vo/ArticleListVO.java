package com.limyel.blog.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleListVO {

    private Integer year;

    private String tag;

    private List<ArticleListItem> list;

    @Data
    public static class ArticleListItem {

        private Integer monthNum;

        private String month;

        private List<Article> articles;

    }

    @Data
    public static class Article {

        private String title;

        private String slug;

        private String createTime;

    }

}
