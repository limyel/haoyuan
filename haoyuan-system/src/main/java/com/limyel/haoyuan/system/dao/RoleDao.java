package com.limyel.haoyuan.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleDao extends BaseDao<RoleEntity> {

    default List<RoleEntity> selectByIds(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapper<RoleEntity>()
                .in(RoleEntity::getId, ids));
    }

    default RoleEntity selectByName(String name) {
        return selectOne(new LambdaQueryWrapperPlus<RoleEntity>()
                .eqIfPresent(RoleEntity::getName, name));
    }

    default RoleEntity selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<RoleEntity>()
                .eqIfPresent(RoleEntity::getCode, code));
    }

}