package com.limyel.haoyuan.mall.sys.service;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.common.sys.entity.SysUserEntity;
import com.limyel.haoyuan.mall.sys.dao.SysUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserEntity getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectOne(SysUserEntity::getUsername, username);
        if (sysUser == null) {
            throw new ServiceException("用户不存在");
        }
        return sysUser;
    }

}
