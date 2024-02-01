package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseDao<RoleDO> {

    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperPlus<RoleDO>()
                .eqIfPresent(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<RoleDO>()
                .eqIfPresent(RoleDO::getCode, code));
    }

}
