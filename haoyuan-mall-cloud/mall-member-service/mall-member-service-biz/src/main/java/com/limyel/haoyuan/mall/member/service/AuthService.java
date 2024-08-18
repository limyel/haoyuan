package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.mall.member.dto.auth.LoginDTO;
import com.limyel.haoyuan.mall.member.vo.auth.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("userAuthService")
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public LoginVO login(LoginDTO dto) {
        return null;
    }

}
