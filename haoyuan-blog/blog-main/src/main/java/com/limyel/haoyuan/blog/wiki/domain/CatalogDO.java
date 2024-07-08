package com.limyel.haoyuan.blog.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wiki_catalog")
public class CatalogDO extends BaseDO {

    /**
     * wiki ID
     */
    private Long wikiId;

    /**
     * 文章 ID
     */
    private Long postId;

    /**
     * 文章 slug
     */
    private String postSlug;

    /**
     * 标题
     */
    private String title;

    /**
     * 目录层级
     */
    private Integer level;

    /**
     * 父目录 ID
     */
    private Long pid;

    /**
     * 显示顺序
     */
    private Integer sort;

}
