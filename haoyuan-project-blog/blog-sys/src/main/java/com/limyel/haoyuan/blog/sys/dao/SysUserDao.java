package com.limyel.haoyuan.blog.sys.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.sys.entity.SysUserEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    default int updatePassword(String username, String password) {
        return update(new LambdaUpdateWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username)
                .set(SysUserEntity::getPassword, password));
    }

}
