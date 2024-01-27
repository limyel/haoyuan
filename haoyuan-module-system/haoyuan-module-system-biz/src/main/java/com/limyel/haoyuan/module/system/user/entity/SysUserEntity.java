package com.limyel.haoyuan.module.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUserEntity extends TenantBaseEntity {

    private String username;

    private String password;

    private Long deptId;

    private Set<Long> postIds;

    private String email;

    private String mobile;

    private Integer gender;

    private String avatar;

    private Boolean superAdmin;

    private Integer status;

    private String remark;

}
