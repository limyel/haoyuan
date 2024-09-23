package com.limyel.haoyuan.blog.common.main.entity;

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
     * 标签
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 是否是系统设置
     */
    private Boolean secret;

}
