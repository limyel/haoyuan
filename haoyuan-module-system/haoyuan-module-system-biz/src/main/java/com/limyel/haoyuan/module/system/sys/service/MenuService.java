package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.entity.MenuDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Long create(MenuDTO dto);

    void update(MenuDTO dto);

    void delete(Long id);

    MenuDO get(Long id);

    Set<String> listPermissionsByRoleIds(List<Long> roleIds);

}
