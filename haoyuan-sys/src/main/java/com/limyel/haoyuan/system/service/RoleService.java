package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.domain.RoleDO;
import com.limyel.haoyuan.system.dto.role.RoleDTO;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    Long create(RoleDTO dto);

    void update(RoleDTO dto);

    void delete(Long id);

    RoleDO get(Long id);

    List<RoleDO> list(Collection<Long> ids);

}