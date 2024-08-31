package com.limyel.haoyuan.mall.member.controller.app;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.satoken.annotation.SaUserCheckLogin;
import com.limyel.haoyuan.mall.member.service.UserService;
import com.limyel.haoyuan.mall.member.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaUserCheckLogin
    @GetMapping("/get/me")
    @SentinelResource("getCurrentUserInfo")
    public R<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO result = userService.getCurrentUserInfo();
        return R.ok(result);
    }

}
