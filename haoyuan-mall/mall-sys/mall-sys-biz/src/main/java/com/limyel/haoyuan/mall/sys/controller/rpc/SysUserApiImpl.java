package com.limyel.haoyuan.mall.sys.controller.rpc;

import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;
import com.limyel.haoyuan.mall.common.sys.convert.SysUserConvert;
import com.limyel.haoyuan.mall.common.sys.entity.SysUserEntity;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import com.limyel.haoyuan.mall.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SysUserApiImpl implements SysUserApi {

    private final SysUserService sysUserService;

    @Override
    public SysUserSecurity getByUsername(String username) {
        SysUserEntity sysUser = sysUserService.getByUsername(username);
        return SysUserConvert.INSTANCE.toSecurity(sysUser);
    }
}
