package com.limyel.haoyuan.blog.main.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("main_post_content")
public class PostContentDO extends BaseDO {

    @TableId
    private Long id;

    private Long postId;

    private String content;

    @TableLogic
    private LocalDateTime deleteTime;

}
