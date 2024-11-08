package com.limyel.haoyuan.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.cloud.sys.constant.RoleTypeEnum;
import com.limyel.haoyuan.cloud.sys.convert.RoleConvert;
import com.limyel.haoyuan.cloud.sys.dao.RoleDao;
import com.limyel.haoyuan.cloud.sys.dto.role.RoleDTO;
import com.limyel.haoyuan.cloud.sys.dto.role.RolePageDTO;
import com.limyel.haoyuan.cloud.sys.entity.RoleEntity;
import com.limyel.haoyuan.cloud.sys.vo.role.RolePageVO;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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

        int result = roleDao.updateById(role);
        // todo 清除角色在线的用户

        return result;
    }

    public PageData<RolePageVO> getPage(RolePageDTO dto) {
        Page<RoleEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(dto.getName()), RoleEntity::getName, dto.getName())
                .like(StringUtils.hasText(dto.getCode()), RoleEntity::getCode, dto.getCode())
                .eq(Objects.nonNull(dto.getStatus()), RoleEntity::getStatus, dto.getStatus());
        roleDao.selectPage(page, queryWrapper);

        List<RolePageVO> list = page.getRecords().stream()
                .map(RoleConvert.INSTANCE::toPageVO)
                .toList();
        return new PageData<>(page, list);
    }

    public RoleDTO getById(Long id) {
        RoleEntity role = roleDao.selectById(id);
        if (Objects.isNull(role)) {
            throw new ServiceException("不允许访问不存在的角色");
        }

        return RoleConvert.INSTANCE.toDTO(role);
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
