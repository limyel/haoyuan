package com.limyel.haoyuan.module.system.user.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.user.dd.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    default SysUserEntity selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserEntity>()
                .eqIfPresent(SysUserEntity::getUsername, username));
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
