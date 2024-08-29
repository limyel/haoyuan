package com.limyel.haoyuan.mall.sys.api;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.sys.convert.SysUserConvert;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfo;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import com.limyel.haoyuan.mall.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SysUserApiImpl implements SysUserApi {

    private final SysUserService sysUserService;

    @Override
    public SysUserSecurity getByUsername(String username) {
        SysUserEntity sysUser = sysUserService.getByUsername(username)
                .orElseThrow(
                        () -> new ServiceException("用户名或密码错误")
                );
        return SysUserConvert.INSTANCE.toSecurity(sysUser);
    }

    @Override
    public SysUserInfo getById(Long id) {
        SysUserInfo result = sysUserService.getInfo(id);
        return result;
    }

}
