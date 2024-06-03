package com.limyel.haoyuan.system.service;

import java.util.List;

public interface RoleMenuService {

    void create(Long roleId, Long menuId);

    List<Long> listMenuIdsByRoleIds(List<Long> roleIds);

}