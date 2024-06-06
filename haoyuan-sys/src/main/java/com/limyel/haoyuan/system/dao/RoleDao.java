package com.limyel.haoyuan.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleDao extends BaseDao<RoleDO> {

    default List<RoleDO> selectByIds(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapper<RoleDO>()
                .in(RoleDO::getId, ids));
    }

    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperPlus<RoleDO>()
                .eqIfPresent(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<RoleDO>()
                .eqIfPresent(RoleDO::getCode, code));
    }

}