package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("menu")
public class MenuEntity extends BaseEntity {

    /**
     * 菜单名
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long pid;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单类型，0-目录，1-菜单，2-按钮
     */
    private Integer type;

    /**
     * 是否显示
     */
    private Boolean visible;

    /**
     * 状态，0-禁用，1-正常
     */
    private Integer status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

}
