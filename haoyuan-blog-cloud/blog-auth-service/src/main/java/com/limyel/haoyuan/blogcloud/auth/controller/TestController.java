package com.limyel.haoyuan.blogcloud.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/login")
    public R<?> login(String username, String password) {
        if ("limyel".equals(username) && "123456".equals(password)) {
            StpUtil.login(1);
            return R.ok();
        } else {
            return R.failed();
        }
    }



}
