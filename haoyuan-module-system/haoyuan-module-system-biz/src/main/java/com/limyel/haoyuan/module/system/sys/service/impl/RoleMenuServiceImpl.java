package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.module.system.sys.dao.RoleMenuDao;
import com.limyel.haoyuan.module.system.sys.entity.RoleMenuDO;
import com.limyel.haoyuan.module.system.sys.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public void create(Long roleId, Long menuId) {

    }

    @Override
    public Set<Long> listMenuIdsByRoleIds(List<Long> roleIds) {
        List<RoleMenuDO> list = roleMenuDao.selectByRoleIds(roleIds);
        return list.stream()
                .map(RoleMenuDO::getMenuId)
                .collect(Collectors.toSet());
    }

}
