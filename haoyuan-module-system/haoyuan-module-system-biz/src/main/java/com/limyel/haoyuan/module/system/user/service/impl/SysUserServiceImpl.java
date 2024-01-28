package com.limyel.haoyuan.module.system.user.service.impl;

import com.limyel.haoyuan.common.exception.ServiceException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.user.dao.SysUserDao;
import com.limyel.haoyuan.module.system.user.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import com.limyel.haoyuan.module.system.user.dto.SysUserFilterDTO;
import com.limyel.haoyuan.module.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Long create(SysUserDTO dto) {
        // todo 租户
        validateUsernameUnique(null, dto.getUsername());
        validateMobileUnique(null, dto.getMobile());
        validateEmailUnique(null, dto.getEmail());



        return null;
    }

    @Override
    public void update(SysUserDTO dto) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SysUserDO get(Long id) {
        return null;
    }

    @Override
    public List<SysUserDO> getList(SysUserFilterDTO dto) {
        return null;
    }

    @Override
    public PageData<SysUserDO> getPage(SysUserFilterDTO dto) {
        return null;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysUserDO sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new ServiceException(SysErrorCodeConstant.USER_NOT_FOUND);
        }
    }

    private void validateUsernameUnique(Long id, String username) {
        SysUserDO sysUser = sysUserDao.selectByUsername(username);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new ServiceException(SysErrorCodeConstant.USER_USERNAME_DUPLICATE);
        }
    }

    private void validateMobileUnique(Long id, String mobile) {
        SysUserDO sysUser = sysUserDao.selectByMobile(mobile);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new ServiceException(SysErrorCodeConstant.USER_MOBILE_DUPLICATE);
        }
    }

    private void validateEmailUnique(Long id, String email) {
        SysUserDO sysUser = sysUserDao.selectByEmail(email);
        if (sysUser == null) {
            return;
        }
        if (id == null) {
            return;
        }
        if (!Objects.equals(id, sysUser.getId())) {
            throw new ServiceException(SysErrorCodeConstant.USER_EMAIL_DUPLICATE);
        }
    }

}
