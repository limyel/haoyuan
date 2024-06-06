package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleDO> {

    default List<SysUserRoleDO> selectByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperPlus<SysUserRoleDO>()
                .eqIfPresent(SysUserRoleDO::getUserId, userId));
    }

}