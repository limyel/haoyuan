package com.limyel.haoyuan.blog.main.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_tag")
public class TagDO extends BaseDO {

    private String name;

    private String slug;

    private String remark;

}
