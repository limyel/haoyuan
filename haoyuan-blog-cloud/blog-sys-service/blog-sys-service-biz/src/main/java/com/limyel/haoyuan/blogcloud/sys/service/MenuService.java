package com.limyel.haoyuan.blogcloud.sys.service;

import com.limyel.haoyuan.blogcloud.sys.constant.MenuTypeEnum;
import com.limyel.haoyuan.blogcloud.sys.convert.MenuConvert;
import com.limyel.haoyuan.blogcloud.sys.dao.MenuDao;
import com.limyel.haoyuan.blogcloud.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.blogcloud.sys.entity.MenuEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    private final RoleMenuService roleMenuService;

    public Long create(MenuDTO dto) {
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuEntity menu = MenuConvert.INSTANCE.toEntity(dto);
        menuDao.insert(menu);
        return menu.getId();
    }

    public void update(MenuDTO dto) {
        validateExist(dto.getId());
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuEntity menu = MenuConvert.INSTANCE.toEntity(dto);
        menuDao.updateById(menu);
    }

    public void delete(Long id) {
        validateExist(id);
        menuDao.deleteById(id);
    }

    public MenuEntity get(Long id) {
        return menuDao.selectById(id);
    }

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
            throw new ServiceException();
        }
    }

    private void validatePid(Long pid, Long id) {
        if (pid == null || MenuEntity.ROOT_ID.equals(pid)) {
            return;
        }
        if (pid.equals(id)) {
            throw new ServiceException();
        }
        MenuEntity pMenu = menuDao.selectById(pid);
        if (pMenu == null) {
            throw new ServiceException();
        }
        if (!MenuTypeEnum.DIR.getType().equals(pMenu.getType())
                && !MenuTypeEnum.MENU.getType().equals(pMenu.getType())) {
            throw new ServiceException();
        }
    }

    private void validateNameUnique(Long pid, Long id, String name) {
        MenuEntity menu = menuDao.selectByPidAndName(pid, name);
        if (menu == null) {
            return;
        }
        if (id == null) {
            throw new ServiceException();
        }
        if (!Objects.equals(id, menu.getId())) {
            throw new ServiceException();
        }
    }

}