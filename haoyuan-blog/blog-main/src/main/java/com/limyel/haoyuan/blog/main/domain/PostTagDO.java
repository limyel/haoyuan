package com.limyel.haoyuan.blog.main.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_post_tag")
public class PostTagDO extends BaseDO {

    private Long postId;

    private Long tagId;

}
