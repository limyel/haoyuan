package com.limyel.haoyuan.module.system.auth.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.auth.service.UserTokenService;
import com.limyel.haoyuan.module.system.auth.vo.TokenVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "认证")
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/login")
    public Result<TokenVO> login() {
        return null;
    }

}
