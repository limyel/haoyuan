package com.limyel.haoyuan.mall.auth.controller;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.auth.service.AuthService;
import com.limyel.haoyuan.mall.common.auth.dto.LoginDTO;
import com.limyel.haoyuan.mall.common.auth.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "用户认证")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation("登录接口")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO dto) {
        LoginVO result = authService.login(dto);
        return R.ok(result);
    }

}
