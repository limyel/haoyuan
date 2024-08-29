package com.limyel.haoyuan.mall.sys.service;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JwtUtil;
import com.limyel.haoyuan.mall.security.entity.LoginUser;
import com.limyel.haoyuan.mall.security.vo.LoginVO;
import com.limyel.haoyuan.mall.sys.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("sysAuthService")
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public LoginVO login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            throw new ServiceException("账号名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        LoginVO result = new LoginVO();
        result.setAccessToken(jwtUtil.generateToken(loginUser.getUsername()));

        return result;
    }

}
