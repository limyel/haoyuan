package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class SysUserRoleEntity {

    /**
     * 用户ID
     */
    private Long sysUserId;

    /**
     * 角色ID
     */
    private Long roleId;

}
