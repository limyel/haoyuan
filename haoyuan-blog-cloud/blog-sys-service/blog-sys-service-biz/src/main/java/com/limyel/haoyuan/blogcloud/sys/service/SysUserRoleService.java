package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.dao.UserRoleDao;
import com.limyel.haoyuan.blogcloud.sys.entity.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserRoleService {

    private final UserRoleDao userRoleDao;

    public List<Long> listRoleIdByUserId(Long userId) {
        List<UserRoleEntity> list = userRoleDao.selectByUserId(userId);
        return list.stream()
                .map(UserRoleEntity::getRoleId)
                .collect(Collectors.toList());
    }

}