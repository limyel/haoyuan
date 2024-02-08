package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {

    default List<RoleMenuDO> selectByRoleIds(List<Long> roleIds) {
        return selectList(new LambdaQueryWrapperPlus<RoleMenuDO>()
                .inIfPresent(RoleMenuDO::getRoleId, roleIds));
    }

}
