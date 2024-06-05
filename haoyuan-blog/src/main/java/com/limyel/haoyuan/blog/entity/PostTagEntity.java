package com.limyel.haoyuan.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("blog_post_tag")
public class PostTagEntity extends BaseEntity {

    private Long postId;

    private Long tagId;

}
