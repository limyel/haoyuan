package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.system.constant.RoleCodeEnum;
import com.limyel.haoyuan.system.constant.RoleTypeEnum;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.RoleConvert;
import com.limyel.haoyuan.system.dao.RoleDao;
import com.limyel.haoyuan.system.domain.RoleDO;
import com.limyel.haoyuan.system.dto.role.RoleDTO;
import com.limyel.haoyuan.system.service.RoleService;
import com.limyel.haoyuan.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public Long create(RoleDTO dto) {
        validateCodeUnique(dto.getId(), dto.getCode());
        validateNameUnique(dto.getId(), dto.getName());

        RoleDO role = RoleConvert.INSTANCE.toDO(dto);
        roleDao.insert(role);
        return role.getId();
    }

    @Override
    public void update(RoleDTO dto) {
        validateType(dto.getId());
        validateCodeUnique(dto.getId(), dto.getCode());
        validateNameUnique(dto.getId(), dto.getName());

        RoleDO role = RoleConvert.INSTANCE.toDO(dto);
        roleDao.updateById(role);
    }

    @Override
    public void delete(Long id) {
        validateType(id);
        roleDao.deleteById(id);
    }

    @Override
    public RoleDO get(Long id) {
        return roleDao.selectById(id);
    }

    @Override
    public List<RoleDO> list(Collection<Long> ids) {
        return roleDao.selectByIds(ids);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        RoleDO role = roleDao.selectById(id);
        if (role == null) {
            throw new BizException(SysErrorCode.ROLE_NOT_FOUND);
        }
    }

    private void validateNameUnique(Long id, String name) {
        RoleDO role = roleDao.selectByName(name);
        if (role == null) {
            return;
        }
        if (id == null) {
            throw new BizException();
        }
        if (!Objects.equals(id, role.getId())) {
            throw new BizException();
        }
    }

    private void validateCodeUnique(Long id, String code) {
        if (RoleCodeEnum.isSuperAdmin(code)) {
            throw new BizException();
        }
        RoleDO role = roleDao.selectByCode(code);
        if (role == null) {
            return;
        }
        if (id == null) {
            throw new BizException();
        }
        if (!Objects.equals(id, role.getId())) {
            throw new BizException();
        }
    }

    private void validateType(Long id) {
        validateExist(id);
        RoleDO role = roleDao.selectById(id);
        if (RoleTypeEnum.SYSTEM.getType().equals(role.getType())) {
            throw new BizException();
        }
    }

}