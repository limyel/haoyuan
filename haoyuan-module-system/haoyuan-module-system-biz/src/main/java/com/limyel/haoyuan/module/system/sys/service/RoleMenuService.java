package com.limyel.haoyuan.module.system.sys.service;

import java.util.List;
import java.util.Set;

public interface RoleMenuService {

    void create(Long roleId, Long menuId);

    Set<Long> listMenuIdsByRoleIds(List<Long> roleIds);

}
