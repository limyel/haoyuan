package com.limyel.haoyuan.system.service.impl;

import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.system.constant.MenuTypeEnum;
import com.limyel.haoyuan.system.constant.SysErrorCode;
import com.limyel.haoyuan.system.convert.MenuConvert;
import com.limyel.haoyuan.system.dao.MenuDao;
import com.limyel.haoyuan.system.entity.MenuEntity;
import com.limyel.haoyuan.system.dto.menu.MenuDTO;
import com.limyel.haoyuan.system.service.MenuService;
import com.limyel.haoyuan.system.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public Long create(MenuDTO dto) {
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuEntity menu = MenuConvert.INSTANCE.toDO(dto);
        menuDao.insert(menu);
        return menu.getId();
    }

    @Override
    public void update(MenuDTO dto) {
        validateExist(dto.getId());
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuEntity menu = MenuConvert.INSTANCE.toDO(dto);
        menuDao.updateById(menu);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        menuDao.deleteById(id);
    }

    @Override
    public MenuEntity get(Long id) {
        return menuDao.selectById(id);
    }

    @Override
    public List<String> listPermissionsByRoleIds(List<Long> roleIds) {
        List<Long> menuIds = roleMenuService.listMenuIdsByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(menuIds)) {
            return new ArrayList<>();
        }
        List<MenuEntity> list = menuDao.selectByIds(menuIds);
        List<String[]> permissionsList = list.stream().map(menu -> {
            String permissions = menu.getPermissions();
            return permissions.split(",");
        }).collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (String[] permissions : permissionsList) {
            result.addAll(Arrays.asList(permissions));
        }
        // 移除空的权限
        result.remove("");
        return result;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        MenuEntity menu = menuDao.selectById(id);
        if (menu == null) {
            throw new BizException(SysErrorCode.MENU_NOT_FOUND);
        }
    }

    private void validatePid(Long pid, Long id) {
        if (pid == null || MenuEntity.ROOT_ID.equals(pid)) {
            return;
        }
        if (pid.equals(id)) {
            throw new BizException(SysErrorCode.MENU_PID_ERROR);
        }
        MenuEntity pMenu = menuDao.selectById(pid);
        if (pMenu == null) {
            throw new BizException(SysErrorCode.MENU_PID_NOT_FOUND);
        }
        if (!MenuTypeEnum.DIR.getType().equals(pMenu.getType())
                && !MenuTypeEnum.MENU.getType().equals(pMenu.getType())) {
            throw new BizException(SysErrorCode.MENU_PID_NOT_DIR_OR_MENU);
        }
    }

    private void validateNameUnique(Long pid, Long id, String name) {
        MenuEntity menu = menuDao.selectByPidAndName(pid, name);
        if (menu == null) {
            return;
        }
        if (id == null) {
            throw new BizException(SysErrorCode.MENU_NAME_DUPLICATE);
        }
        if (!Objects.equals(id, menu.getId())) {
            throw new BizException(SysErrorCode.MENU_NAME_DUPLICATE);
        }
    }

}