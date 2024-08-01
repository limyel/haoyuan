package com.limyel.haoyuan.blogcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_menu")
public class RoleMenuEntity extends BaseEntity {

    private Long roleId;

    private Long menuId;

}