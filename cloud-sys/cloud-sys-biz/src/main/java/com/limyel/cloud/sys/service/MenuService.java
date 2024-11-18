package com.limyel.cloud.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.cloud.sys.dao.MenuDao;
import com.limyel.cloud.sys.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    private final RoleMenuService roleMenuService;

    private final SysUserRoleService sysUserRoleService;

    public Set<String> getPermissions(Long sysUserId) {
        List<Long> roleIdList = sysUserRoleService.getRoleIdList(sysUserId);
        if (roleIdList.isEmpty()) {
            return Collections.emptySet();
        }

        List<Long> menuIdList = roleMenuService.getMenuIdList(roleIdList);

        List<MenuEntity> menus = menuDao.selectList(new LambdaQueryWrapper<MenuEntity>()
                .in(MenuEntity::getId, menuIdList));
        if (menus.isEmpty()) {
            return Collections.emptySet();
        }
        return menus.stream()
                .flatMap(menu -> Stream.of(menu.getPerms().split(",")))
                .collect(Collectors.toSet());
    }

}
