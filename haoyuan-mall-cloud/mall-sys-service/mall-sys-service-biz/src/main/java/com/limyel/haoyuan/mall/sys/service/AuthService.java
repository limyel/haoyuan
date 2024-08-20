package com.limyel.haoyuan.mall.sys.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.CryptUtil;
import com.limyel.haoyuan.mall.sys.dto.auth.LoginDTO;
import com.limyel.haoyuan.mall.sys.entity.SysUserEntity;
import com.limyel.haoyuan.mall.sys.vo.auth.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("sysAuthService")
@RequiredArgsConstructor
public class AuthService {

    private final SysUserService sysUserService;

    public LoginVO login(LoginDTO dto) {
        SysUserEntity sysUser = sysUserService.getByUsername(dto.getUsername());
        boolean match = CryptUtil.match(dto.getPassword(), sysUser.getPassword());
        if (!match) {
            throw new ServiceException();
        }
        StpUtil.login(sysUser.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        LoginVO result = new LoginVO();
        result.setAccessToken(tokenInfo.tokenValue);
        result.setExpireIn(tokenInfo.tokenTimeout);
        return result;
    }

}
