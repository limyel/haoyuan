package com.limyel.haoyuan.blog.admin.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.admin.domain.UserDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<UserDO> {

    default int updatePassword(String username, String password) {
        return update(new LambdaUpdateWrapper<UserDO>()
                .eq(UserDO::getUsername, username)
                .set(UserDO::getPassword, password));
    }

}