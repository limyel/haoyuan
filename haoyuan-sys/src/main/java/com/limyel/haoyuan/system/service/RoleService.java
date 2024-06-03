package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.system.entity.RoleEntity;
import com.limyel.haoyuan.system.dto.role.RoleDTO;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    Long create(RoleDTO dto);

    void update(RoleDTO dto);

    void delete(Long id);

    RoleEntity get(Long id);

    List<RoleEntity> list(Collection<Long> ids);

}