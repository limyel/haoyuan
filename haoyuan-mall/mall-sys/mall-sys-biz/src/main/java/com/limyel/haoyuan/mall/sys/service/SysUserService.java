package com.limyel.haoyuan.mall.sys.service;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.sys.convert.SysUserConvert;
import com.limyel.haoyuan.mall.sys.dao.SysUserDao;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfo;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserEntity getById(Long id) {
        SysUserEntity sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new ServiceException("用户不存在");
        }
        return sysUser;
    }

    public SysUserInfo getInfo(Long id) {
        SysUserEntity sysUser = getById(id);
        return SysUserConvert.INSTANCE.toInfo(sysUser);
    }

    public Optional<SysUserEntity> getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectOne(SysUserEntity::getUsername, username);
        return Optional.ofNullable(sysUser);
    }

}
