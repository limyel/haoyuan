package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.constant.RoleCodeEnum;
import com.limyel.haoyuan.blogcloud.sys.convert.RoleConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.RoleDao;
import com.limyel.haoyuan.blogcloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDao roleDao;

    private final SysUserRoleService sysUserRoleService;

    public Long create(RoleDTO dto) {
        validateCodeUnique(dto.getId(), dto.getCode());
        validateNameUnique(dto.getId(), dto.getName());

        RoleEntity role = RoleConvert.INSTANCE.toEntity(dto);
        roleDao.insert(role);
        return role.getId();
    }

    public void update(RoleDTO dto) {
        validateCodeUnique(dto.getId(), dto.getCode());
        validateNameUnique(dto.getId(), dto.getName());

        RoleEntity role = RoleConvert.INSTANCE.toEntity(dto);
        roleDao.updateById(role);
    }

    public void delete(Long id) {
        roleDao.deleteById(id);
    }

    public RoleEntity get(Long id) {
        return roleDao.selectById(id);
    }

    public List<RoleEntity> list(Collection<Long> ids) {
        return roleDao.selectByIds(ids);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        RoleEntity role = roleDao.selectById(id);
        if (role == null) {
            throw new ServiceException();
        }
    }

    private void validateNameUnique(Long id, String name) {
        RoleEntity role = roleDao.selectByName(name);
        if (role == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, role.getId())) {
            throw new ServiceException();
        }
    }

    private void validateCodeUnique(Long id, String code) {
        if (RoleCodeEnum.isSuperAdmin(code)) {
            throw new ServiceException();
        }
        RoleEntity role = roleDao.selectByCode(code);
        if (role == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, role.getId())) {
            throw new ServiceException();
        }
    }

}