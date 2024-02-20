package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao extends BaseDao<UserRoleDO> {

    default List<UserRoleDO> selectByUserId(Long userId) {
        return selectList(new LambdaQueryWrapperPlus<UserRoleDO>()
                .eqIfPresent(UserRoleDO::getUserId, userId));
    }

}
