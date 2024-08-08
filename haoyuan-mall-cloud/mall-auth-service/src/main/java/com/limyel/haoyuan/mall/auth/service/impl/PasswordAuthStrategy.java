package com.limyel.haoyuan.mall.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.CryptUtil;
import com.limyel.haoyuan.common.core.util.JsonUtil;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.auth.dto.PasswordAuthDTO;
import com.limyel.haoyuan.mall.auth.service.AuthStrategy;
import com.limyel.haoyuan.mall.auth.vo.LoginVO;
import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("password" + AuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuthStrategy implements AuthStrategy {

    private final SysUserApi sysUserApi;

    private final UserApi userApi;

    @Override
    public LoginVO login(String authInfo, String loginType) {
        PasswordAuthDTO authDTO = JsonUtil.parseObject(authInfo, PasswordAuthDTO.class);
        boolean match = false;
        LoginVO result = new LoginVO();

        if ("admin".equals(loginType)) {
            SysUserInfoDTO sysUser = sysUserApi.getByUsername(authDTO.getUsername());
            match = CryptUtil.match(authDTO.getPassword(), sysUser.getPassword());
            if (!match) {
                throw new ServiceException();
            }
            StpUtil.login(sysUser.getId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            result.setAccessToken(tokenInfo.tokenValue);
            result.setExpireIn(tokenInfo.tokenTimeout);
        } else if ("user".equals(loginType)) {
            UserInfoDTO user = userApi.getByUsername(authDTO.getUsername());
            match = CryptUtil.match(authDTO.getPassword(), user.getPassword());
            if (!match) {
                throw new ServiceException();
            }
            StpUserUtil.login(user.getId());
            SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();
            result.setAccessToken(tokenInfo.tokenValue);
            result.setExpireIn(tokenInfo.tokenTimeout);
        }

        return result;
    }

}
