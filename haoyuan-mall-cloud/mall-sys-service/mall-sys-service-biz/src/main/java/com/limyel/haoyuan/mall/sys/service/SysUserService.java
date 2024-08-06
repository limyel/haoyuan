package com.limyel.haoyuan.mall.sys.service;

import com.limyel.haoyuan.mall.sys.convert.SysUserConvert;
import com.limyel.haoyuan.mall.sys.dao.SysUserDao;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfoDTO;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserInfoDTO getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectOne(SysUserEntity::getUsername, username);
        if (sysUser == null) {

        }
        return SysUserConvert.INSTANCE.toInfoDTO(sysUser);
    }

}
