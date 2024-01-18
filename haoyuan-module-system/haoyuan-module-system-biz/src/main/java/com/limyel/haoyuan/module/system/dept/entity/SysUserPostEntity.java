package com.limyel.haoyuan.module.system.dept.entity;

import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseEntity;
import lombok.Data;

@Data
public class SysUserPostEntity extends TenantBaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

}
