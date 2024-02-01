package com.limyel.haoyuan.module.system.sys.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.module.system.constant.MenuTypeEnum;
import com.limyel.haoyuan.module.system.exception.SysErrorCode;
import com.limyel.haoyuan.module.system.sys.convert.DictTypeConvert;
import com.limyel.haoyuan.module.system.sys.convert.MenuConvert;
import com.limyel.haoyuan.module.system.sys.dao.MenuDao;
import com.limyel.haoyuan.module.system.sys.dataobject.DictTypeDO;
import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import com.limyel.haoyuan.module.system.sys.dto.dict.type.DictTypeDTO;
import com.limyel.haoyuan.module.system.sys.dto.menu.MenuDTO;
import com.limyel.haoyuan.module.system.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Long create(MenuDTO dto) {
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuDO menu = MenuConvert.INSTANCE.toDO(dto);
        menuDao.insert(menu);
        return menu.getId();
    }

    @Override
    public void update(MenuDTO dto) {
        validateExist(dto.getId());
        validatePid(dto.getPid(), dto.getId());
        validateNameUnique(dto.getPid(), dto.getId(), dto.getName());

        MenuDO menu = MenuConvert.INSTANCE.toDO(dto);
        menuDao.updateById(menu);
    }

    @Override
    public void delete(Long id) {
        validateExist(id);
        menuDao.deleteById(id);
    }

    @Override
    public MenuDO get(Long id) {
        return menuDao.selectById(id);
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        MenuDO menu = menuDao.selectById(id);
        if (menu == null) {
            throw new BizException(SysErrorCode.MENU_NOT_FOUND);
        }
    }

    private void validatePid(Long pid, Long id) {
        if (pid == null || MenuDO.ROOT_ID.equals(pid)) {
            return;
        }
        if (pid.equals(id)) {
            throw new BizException(SysErrorCode.MENU_PID_ERROR);
        }
        MenuDO pMenu = menuDao.selectById(pid);
        if (pMenu == null) {
            throw new BizException(SysErrorCode.MENU_PID_NOT_FOUND);
        }
        if (!MenuTypeEnum.DIR.getType().equals(pMenu.getType())
                && !MenuTypeEnum.MENU.getType().equals(pMenu.getType())) {
            throw new BizException(SysErrorCode.MENU_PID_NOT_DIR_OR_MENU);
        }
    }

    private void validateNameUnique(Long pid, Long id, String name) {
        MenuDO menu = menuDao.selectByPidAndName(pid, name);
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
