package com.limyel.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("article")
public class ArticleEntity extends BaseEntity {

    private String title;

    private String slug;

    private String content;

    private Boolean top;

    private Integer status;

    private Integer viewNum = 0;

}
