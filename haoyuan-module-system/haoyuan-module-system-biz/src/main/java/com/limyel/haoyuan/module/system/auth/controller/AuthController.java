package com.limyel.haoyuan.module.system.auth.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.auth.dto.LoginDTO;
import com.limyel.haoyuan.module.system.auth.service.AuthService;
import com.limyel.haoyuan.module.system.auth.service.UserTokenService;
import com.limyel.haoyuan.module.system.auth.vo.LoginVO;
import com.limyel.haoyuan.module.system.sys.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "认证")
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        LoginVO result = authService.login(dto);
        return Result.ok(result);
    }

}
