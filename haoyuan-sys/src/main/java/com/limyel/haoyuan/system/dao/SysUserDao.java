package com.limyel.haoyuan.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    default SysUserEntity selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username));
    }

    default SysUserEntity selectByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserEntity>()
                .eqIfPresent(SysUserEntity::getMobile, mobile));
    }

    default SysUserEntity selectByEmail(String email) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserEntity>()
                .eqIfPresent(SysUserEntity::getEmail, email));
    }
}