package com.limyel.haoyuan.blog.sys.service;

import com.limyel.haoyuan.blog.sys.dao.UserRoleDao;
import com.limyel.haoyuan.blog.sys.domain.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleDao userRoleDao;

    public List<UserRoleEntity> getByUserId(Long userId) {
        return userRoleDao.selectList(UserRoleEntity::getUserId, userId);
    }

}
