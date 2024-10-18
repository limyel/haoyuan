package com.limyel.haoyuan.cloud.member.dao;

import com.limyel.haoyuan.cloud.member.entity.UserEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

}
