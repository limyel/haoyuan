package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;

public interface MenuService {

    Long create(MenuDTO dto);

    void update(MenuDTO dto);

    void delete(Long id);

    MenuDO get(Long id);

}
