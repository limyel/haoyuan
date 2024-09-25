package com.limyel.haoyuan.mall.auth.service;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.jwt.JwtUtil;
import com.limyel.haoyuan.common.core.jwt.MallTokenPayload;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.core.util.RsaUtil;
import com.limyel.haoyuan.mall.common.auth.constant.GrantTypeEnum;
import com.limyel.haoyuan.mall.common.auth.dto.LoginDTO;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.MemberUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.SysUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.UnLoginUser;
import com.limyel.haoyuan.mall.common.auth.extention.app.AppPasswordAuthenticationToken;
import com.limyel.haoyuan.mall.common.auth.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements InitializingBean {

    private final AuthenticationManager authenticationManager;

    private PrivateKey privateKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        privateKey = RsaUtil.getPrivateKey("jwt_rsa");
    }

    public LoginVO login(LoginDTO dto) {
        Optional<GrantTypeEnum> grantTypeOptional = GrantTypeEnum.from(dto.getGrantType());
        if (grantTypeOptional.isEmpty()) {
            throw new ServiceException("不支持该认证模式");
        }

        GrantTypeEnum grantType = grantTypeOptional.get();
        Authentication authenticate = null;

        if (GrantTypeEnum.APP_PASSWORD.equals(grantType)) {
            UnLoginUser unLoginUser = new UnLoginUser(dto.getUsername(), dto.getPassword());
            AppPasswordAuthenticationToken authenticationToken = new AppPasswordAuthenticationToken(unLoginUser);
            authenticate = authenticationManager.authenticate(authenticationToken);
        }

        if (authenticate == null) {
            throw new ServiceException("登录失败");
        }

        MallUserDetails userDetails = (MallUserDetails) authenticate.getPrincipal();
        MallTokenPayload payload = new MallTokenPayload();
        if (userDetails instanceof SysUserDetails sysUserDetails) {
            payload.setSysUserId(sysUserDetails.getId());
        } else if (userDetails instanceof MemberUserDetails memberUserDetails) {
            payload.setMemberUserId(memberUserDetails.getId());
        }

        String accessToken = JwtUtil.generateJwtToken(JSONUtil.toJson(payload), privateKey, 600);

        LoginVO result = new LoginVO();
        result.setAccessToken(accessToken);
        return result;
    }

}
