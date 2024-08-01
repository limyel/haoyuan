package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.dao.RoleMenuDao;
import com.limyel.haoyuan.blogcloud.sys.entity.RoleMenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleMenuService {

    private final RoleMenuDao roleMenuDao;

    public void create(Long roleId, Long menuId) {

    }

    public List<Long> listMenuIdsByRoleIds(List<Long> roleIds) {
        List<RoleMenuEntity> list = roleMenuDao.selectByRoleIds(roleIds);
        return list.stream()
                .map(RoleMenuEntity::getMenuId)
                .collect(Collectors.toList());
    }

}