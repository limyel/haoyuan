package com.limyel.haoyuan.blog.common.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_post_tag")
public class PostTagEntity extends BaseEntity {

    private Long postId;

    private Long tagId;

}
