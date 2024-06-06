package com.limyel.haoyuan.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserDO> {

    default SysUserDO selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<SysUserDO>()
                .eq(SysUserDO::getUsername, username));
    }

    default SysUserDO selectByMobile(String mobile) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserDO>()
                .eqIfPresent(SysUserDO::getMobile, mobile));
    }

    default SysUserDO selectByEmail(String email) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserDO>()
                .eqIfPresent(SysUserDO::getEmail, email));
    }
}