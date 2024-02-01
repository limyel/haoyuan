package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.module.system.constant.MenuTypeEnum;
import com.limyel.haoyuan.module.system.constant.RoleCodeEnum;
import com.limyel.haoyuan.module.system.constant.RoleTypeEnum;
import com.limyel.haoyuan.module.system.exception.SysErrorCode;
import com.limyel.haoyuan.module.system.sys.convert.MenuConvert;
import com.limyel.haoyuan.module.system.sys.convert.RoleConvert;
import com.limyel.haoyuan.module.system.sys.dao.MenuDao;
import com.limyel.haoyuan.module.system.sys.dao.RoleDao;
import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import com.limyel.haoyuan.module.system.sys.dataobject.RoleDO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.module.system.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.module.system.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

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
