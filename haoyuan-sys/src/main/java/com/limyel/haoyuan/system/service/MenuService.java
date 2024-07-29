package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.MenuEntity;
import com.limyel.haoyuan.system.dto.menu.MenuDTO;

import java.util.List;

public interface MenuService {

    Long create(MenuDTO dto);

    void update(MenuDTO dto);

    void delete(Long id);

    MenuEntity get(Long id);

    List<String> listPermissionsByRoleIds(List<Long> roleIds);

}