package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.system.dao.RoleMenuDao;
import com.limyel.haoyuan.system.domain.RoleMenuEntity;
import com.limyel.haoyuan.system.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public void create(Long roleId, Long menuId) {

    }

    @Override
    public List<Long> listMenuIdsByRoleIds(List<Long> roleIds) {
        List<RoleMenuEntity> list = roleMenuDao.selectByRoleIds(roleIds);
        return list.stream()
                .map(RoleMenuEntity::getMenuId)
                .collect(Collectors.toList());
    }

}