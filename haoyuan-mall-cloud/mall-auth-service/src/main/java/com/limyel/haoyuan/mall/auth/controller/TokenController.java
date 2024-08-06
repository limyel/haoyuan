package com.limyel.haoyuan.mall.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.auth.dto.LoginDTO;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final SysUserApi sysUserApi;

    @PostMapping("/login")
    public R<?> login(@Validated @RequestBody LoginDTO dto) {
        String loginType = dto.getLoginType();



        return R.failed();
    }

    @GetMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }



}
