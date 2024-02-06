package com.limyel.haoyuan.module.system.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserDO> {

    default SysUserDO selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapperPlus<SysUserDO>()
                .eqIfPresent(SysUserDO::getUsername, username));
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
