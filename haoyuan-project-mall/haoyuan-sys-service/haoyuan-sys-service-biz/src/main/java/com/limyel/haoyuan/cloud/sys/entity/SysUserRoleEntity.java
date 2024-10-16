package com.limyel.haoyuan.cloud.sys.entity;

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
