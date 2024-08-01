package com.limyel.haoyuan.blogcloud.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blogcloud.sys.entity.UserEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    default UserEntity selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUsername, username));
    }

}