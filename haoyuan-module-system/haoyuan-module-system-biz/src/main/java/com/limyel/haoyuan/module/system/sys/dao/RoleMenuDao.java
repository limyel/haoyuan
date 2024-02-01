package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.ParamDO;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {

}
