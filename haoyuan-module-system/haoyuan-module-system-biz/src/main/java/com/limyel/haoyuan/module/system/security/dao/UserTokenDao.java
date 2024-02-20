package com.limyel.haoyuan.module.system.security.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.security.entity.UserTokenDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenDao extends BaseDao<UserTokenDO> {

    default UserTokenDO selectByUserId(Long userId) {
        return selectOne(new LambdaQueryWrapperPlus<UserTokenDO>()
                .eqIfPresent(UserTokenDO::getUserId, userId));
    }

    default UserTokenDO selectByToken(String token) {
        return selectOne(new LambdaQueryWrapperPlus<UserTokenDO>()
                .eqIfPresent(UserTokenDO::getToken, token));
    }

}
