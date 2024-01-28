package com.limyel.haoyuan.module.system.user.service.impl;

import com.limyel.haoyuan.common.exception.ServiceException;
import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.constant.SysErrorCodeConstant;
import com.limyel.haoyuan.module.system.user.dao.SysUserDao;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import com.limyel.haoyuan.module.system.user.dto.SysUserFilterDTO;
import com.limyel.haoyuan.module.system.user.dd.SysUserEntity;
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
    public SysUserEntity get(Long id) {
        return null;
    }

    @Override
    public List<SysUserEntity> getList(SysUserFilterDTO dto) {
        return null;
    }

    @Override
    public PageData<SysUserEntity> getPage(SysUserFilterDTO dto) {
        return null;
    }

    private void validateExist(Long id) {
        if (id == null) {
            return;
        }
        SysUserEntity sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new ServiceException(SysErrorCodeConstant.USER_NOT_FOUND);
        }
    }

    private void validateUsernameUnique(Long id, String username) {
        SysUserEntity sysUser = sysUserDao.selectByUsername(username);
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
        SysUserEntity sysUser = sysUserDao.selectByMobile(mobile);
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
        SysUserEntity sysUser = sysUserDao.selectByEmail(email);
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
