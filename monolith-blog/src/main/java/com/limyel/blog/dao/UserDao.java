package com.limyel.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limyel.blog.model.entity.UserEntity;

public interface UserDao extends BaseMapper<UserEntity> {

    UserEntity findByUsername(String username);

}
