package com.limyel.cloud.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.cloud.sys.dao.SysUserDao;
import com.limyel.cloud.sys.model.entity.SysUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    public SysUserEntity getByUsername(String username) {
        SysUserEntity sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username));
        return sysUser;
    }

}
