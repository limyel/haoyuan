package com.limyel.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article_tag")
public class ArticleTagEntity {

    private Long id;

    /**
     * 文章 ID
     */
    private Long articleId;

    /**
     * 标签 ID
     */
    private Long tagId;

}
