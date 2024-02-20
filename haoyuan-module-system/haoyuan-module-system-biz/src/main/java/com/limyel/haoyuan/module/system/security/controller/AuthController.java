package com.limyel.haoyuan.module.system.security.controller;

import com.limyel.haoyuan.framework.web.pojo.Result;
import com.limyel.haoyuan.module.system.security.entity.LoginUser;
import com.limyel.haoyuan.module.system.security.dto.LoginDTO;
import com.limyel.haoyuan.module.system.security.service.AuthService;
import com.limyel.haoyuan.module.system.security.vo.LoginVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = "认证")
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        LoginVO result = authService.login(dto);
        return Result.ok(result);
    }

    @GetMapping("/logout")
    public Result<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        authService.logout(loginUser.getSysUser().getId());

        return new Result<>();
    }

}
