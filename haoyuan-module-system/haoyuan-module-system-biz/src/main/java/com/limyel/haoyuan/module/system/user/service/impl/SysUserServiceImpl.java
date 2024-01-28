package com.limyel.haoyuan.module.system.user.service.impl;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.user.dao.SysUserDao;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import com.limyel.haoyuan.module.system.user.dto.SysUserFilterDTO;
import com.limyel.haoyuan.module.system.user.entity.SysUserEntity;
import com.limyel.haoyuan.module.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Long create(SysUserDTO dto) {
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

    }

    private void validateUsernameUnique(Long id, String username) {

    }

    private void validateMobileUnique(Long id, String mobile) {

    }

    private void validateEmailUnique(Long id, String email) {

    }
}
