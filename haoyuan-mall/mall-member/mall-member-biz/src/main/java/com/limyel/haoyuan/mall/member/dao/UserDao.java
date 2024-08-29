package com.limyel.haoyuan.mall.member.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

}
