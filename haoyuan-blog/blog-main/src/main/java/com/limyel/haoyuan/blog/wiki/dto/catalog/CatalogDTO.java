package com.limyel.haoyuan.blog.wiki.dto.catalog;

import lombok.Data;

@Data
public class CatalogDTO {

    private Long wikiId;

    private Long postId;

    private String postSlug;

    private String title;

    private Integer level;

    private Long pid;

    private Integer sort;

}
