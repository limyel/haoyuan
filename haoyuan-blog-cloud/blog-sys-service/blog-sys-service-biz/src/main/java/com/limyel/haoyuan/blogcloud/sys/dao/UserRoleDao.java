package com.limyel.haoyuan.blogcloud.sys.dao;

import com.limyel.haoyuan.blogcloud.sys.entity.UserRoleEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao extends BaseDao<UserRoleEntity> {

    default List<UserRoleEntity> selectByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperPlus<UserRoleEntity>()
                .eqIfPresent(UserRoleEntity::getUserId, userId));
    }

}