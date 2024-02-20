package com.limyel.haoyuan.module.system.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class MenuDO extends BaseDO {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permissions;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Long pid;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 菜单状态
     */
    private Integer status;

    /**
     * 是否可见
     */
    private Boolean visible;


    public static final Long ROOT_ID = 0L;
}
