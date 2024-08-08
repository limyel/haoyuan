package com.limyel.haoyuan.mall.auth.controller;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.auth.dto.LoginDTO;
import com.limyel.haoyuan.mall.auth.dto.LogoutDTO;
import com.limyel.haoyuan.mall.auth.service.AuthStrategy;
import com.limyel.haoyuan.mall.auth.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO dto) {
        LoginVO result = AuthStrategy.login(dto.getAuthInfo(), dto.getLoginType(), dto.getAuthType());

        return R.ok(result);
    }

    @PostMapping("/logout")
    public R<?> logout(@Validated @RequestBody LogoutDTO dto) {
        return R.ok();
    }


}
