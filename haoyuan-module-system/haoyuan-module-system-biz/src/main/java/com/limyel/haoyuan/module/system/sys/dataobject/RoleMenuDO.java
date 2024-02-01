package com.limyel.haoyuan.module.system.sys.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_menu")
public class RoleMenuDO extends TenantBaseDO {

    private Long roleId;

    private Long menuId;

}
