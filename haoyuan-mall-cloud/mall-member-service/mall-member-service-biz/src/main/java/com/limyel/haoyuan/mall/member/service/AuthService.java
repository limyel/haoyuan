package com.limyel.haoyuan.mall.member.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.CryptUtil;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.member.dto.auth.LoginDTO;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import com.limyel.haoyuan.mall.member.vo.auth.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userAuthService")
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public LoginVO login(LoginDTO dto) {
        UserEntity user = userService.getByUsername(dto.getUsername());
        boolean match = CryptUtil.match(dto.getPassword(), user.getPassword());
        if (!match) {
            throw new ServiceException();
        }
        StpUserUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();

        LoginVO result = new LoginVO();
        result.setAccessToken(tokenInfo.tokenValue);
        result.setExpireIn(tokenInfo.tokenTimeout);
        return result;
    }

}
