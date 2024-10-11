package com.limyel.haoyuan.cloud.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.cloud.sys.constant.RoleTypeEnum;
import com.limyel.haoyuan.cloud.sys.convert.RoleConvert;
import com.limyel.haoyuan.cloud.sys.dao.RoleDao;
import com.limyel.haoyuan.cloud.sys.dto.RoleDTO;
import com.limyel.haoyuan.cloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDao roleDao;

    private final SysUserRoleService sysUserRoleService;

    private final RoleMenuService roleMenuService;

    public int create(RoleDTO dto) {
        // todo 优化
        roleDao.validateUnique(null, RoleEntity::getName, dto.getName(), "角色名已存在");
        roleDao.validateUnique(null, RoleEntity::getCode, dto.getCode(), "角色编码已存在");

        RoleEntity role = RoleConvert.INSTANCE.toEntity(dto);
        int result = roleDao.insert(role);

        // todo 菜单

        return result;
    }

    public int deleteByIds(List<Long> ids) {
        List<RoleEntity> roles = roleDao.selectList(new LambdaQueryWrapper<RoleEntity>()
                .in(RoleEntity::getId, ids));

        for (RoleEntity role : roles) {
            checkSystemRole(role);
            checkSysUserRole(role.getId());
        }

        // 删除角色与菜单关联
        roleMenuService.deleteByRoleIds(ids);
        // todo 部门
        return roleDao.deleteBatchIds(ids);
    }

    public int update(RoleDTO dto) {
        RoleEntity role = RoleConvert.INSTANCE.toEntity(dto);
        checkSystemRole(role);
        roleDao.validateUnique(dto.getId(), RoleEntity::getName, dto.getName(), "角色名已存在");
        roleDao.validateUnique(dto.getId(), RoleEntity::getCode, dto.getCode(), "角色编码已存在");


    }

    private void checkSystemRole(RoleEntity role) {
        if (RoleTypeEnum.SYSTEM.getValue().equals(role.getType())) {
            throw new ServiceException("不允许操作系统内置角色");
        }
    }

    private void checkSysUserRole(Long roleId) {
        Long num = sysUserRoleService.countByRoleId(roleId);
        if (num > 0) {
            throw new ServiceException("不允许删除该角色，存在绑定该角色的用户");
        }
    }

}
