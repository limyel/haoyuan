package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.system.dao.SysUserRoleDao;
import com.limyel.haoyuan.system.domain.SysUserRoleDO;
import com.limyel.haoyuan.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        List<SysUserRoleDO> list = sysUserRoleDao.selectByUserId(userId);
        return list.stream()
                .map(SysUserRoleDO::getRoleId)
                .collect(Collectors.toList());
    }

}