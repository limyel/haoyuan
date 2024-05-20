package com.limyel.haoyuan.system.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.limyel.haoyuan.system.entity.RoleEntity;
import com.limyel.haoyuan.system.service.MenuService;
import com.limyel.haoyuan.system.service.RoleService;
import com.limyel.haoyuan.system.service.SysUserRoleService;
import com.limyel.haoyuan.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final MenuService menuService;

    private final RoleService roleService;

    private final SysUserRoleService sysUserRoleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Long id = Long.valueOf(String.valueOf(loginId));
        List<Long> roleIds = sysUserRoleService.listRoleIdByUserId(id);
        return menuService.listPermissionsByRoleIds(roleIds);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long id = (Long) loginId;
        List<Long> roleIds = sysUserRoleService.listRoleIdByUserId(id);
        List<RoleEntity> roles = roleService.list(roleIds);
        return roles.stream()
                .map(RoleEntity::getCode)
                .collect(Collectors.toList());
    }
}
