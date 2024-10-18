package com.limyel.haoyuan.blog.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.IBaseEntity;
import lombok.Data;

@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements IBaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long sysUserId;

    /**
     * 角色ID
     */
    private Long roleId;

}
