package com.limyel.haoyuan.blog.main.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("main_setting")
public class SettingDO extends BaseDO {

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
