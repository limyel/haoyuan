package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleMenuDO;
import com.limyel.haoyuan.module.system.sys.dataobject.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao extends BaseDao<UserRoleDO> {

}
