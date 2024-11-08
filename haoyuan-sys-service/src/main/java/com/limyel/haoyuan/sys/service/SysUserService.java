package com.limyel.haoyuan.sys.service;

import com.limyel.haoyuan.cloud.sys.dao.SysUserDao;
import com.limyel.haoyuan.cloud.sys.entity.SysUserEntity;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserEntity getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectOne(SysUserEntity::getUsername, username);
        if (sysUser == null) {
            throw new ServiceException();
        }
        return sysUser;
    }

}
