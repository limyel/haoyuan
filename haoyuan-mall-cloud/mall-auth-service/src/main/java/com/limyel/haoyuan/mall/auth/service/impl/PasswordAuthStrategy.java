package com.limyel.haoyuan.mall.auth.service.impl;

import com.limyel.haoyuan.mall.auth.service.AuthStrategy;
import com.limyel.haoyuan.mall.auth.vo.LoginVO;
import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("password" + AuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuthStrategy implements AuthStrategy {

    private final SysUserApi sysUserApi;

    private final UserApi userApi;

    @Override
    public LoginVO login(String authInfo, String loginType) {
        return null;
    }

}
