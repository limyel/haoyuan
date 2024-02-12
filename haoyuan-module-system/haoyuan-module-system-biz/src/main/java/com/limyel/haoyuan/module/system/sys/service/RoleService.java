package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.module.system.sys.dataobject.RoleDO;
import com.limyel.haoyuan.module.system.sys.dto.role.RoleDTO;

import java.util.List;

public interface RoleService {

    Long create(RoleDTO dto);

    void update(RoleDTO dto);

    void delete(Long id);

    RoleDO get(Long id);

    List<Long> listIdsByUserId(Long userId);

}
