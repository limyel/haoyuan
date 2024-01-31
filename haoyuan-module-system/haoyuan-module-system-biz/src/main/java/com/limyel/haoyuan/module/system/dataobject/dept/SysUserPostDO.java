package com.limyel.haoyuan.module.system.dataobject.dept;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUserPostDO extends TenantBaseDO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

}
