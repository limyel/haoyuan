package com.limyel.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tag")
public class TagEntity extends BaseEntity {

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签 slug
     */
    private String slug;

    /**
     * 备注
     */
    private String remark;

}
