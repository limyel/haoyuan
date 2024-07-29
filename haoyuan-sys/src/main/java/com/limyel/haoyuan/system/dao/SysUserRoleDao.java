package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

    default List<SysUserRoleEntity> selectByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperPlus<SysUserRoleEntity>()
                .eqIfPresent(SysUserRoleEntity::getUserId, userId));
    }

}