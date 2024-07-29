package com.limyel.haoyuan.blog.main.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_setting")
public class SettingEntity extends BaseEntity {

    public static final Long DEFAULT_ID = 1L;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 关于
     */
    private String about;

}
