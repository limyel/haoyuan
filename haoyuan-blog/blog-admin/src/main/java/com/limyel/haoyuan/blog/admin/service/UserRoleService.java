package com.limyel.haoyuan.blog.admin.service;

import com.limyel.haoyuan.blog.admin.dao.UserRoleDao;
import com.limyel.haoyuan.blog.admin.domain.UserRoleEntity;
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
