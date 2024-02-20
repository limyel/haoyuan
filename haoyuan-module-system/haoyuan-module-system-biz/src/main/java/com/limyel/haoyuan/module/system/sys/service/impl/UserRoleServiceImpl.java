package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.module.system.sys.dao.UserRoleDao;
import com.limyel.haoyuan.module.system.sys.entity.UserRoleDO;
import com.limyel.haoyuan.module.system.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        List<UserRoleDO> list = userRoleDao.selectByUserId(userId);
        return list.stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toList());
    }
}
