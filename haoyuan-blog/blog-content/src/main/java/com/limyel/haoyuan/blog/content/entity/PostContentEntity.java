package com.limyel.haoyuan.blog.content.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("main_post_content")
public class PostContentEntity extends BaseEntity {

    @TableId
    private Long id;

    private Long postId;

    private String content;

    @TableLogic
    private LocalDateTime deleteTime;

}
