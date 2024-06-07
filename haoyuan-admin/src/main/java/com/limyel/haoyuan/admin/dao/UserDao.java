package com.limyel.haoyuan.admin.dao;

import com.limyel.haoyuan.admin.domain.UserDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<UserDO> {

}