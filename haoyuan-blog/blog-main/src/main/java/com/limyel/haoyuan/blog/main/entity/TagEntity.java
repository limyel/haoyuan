package com.limyel.haoyuan.blog.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_tag")
public class TagEntity extends BaseEntity {

    private String name;

    private String slug;

    private String remark;

}
