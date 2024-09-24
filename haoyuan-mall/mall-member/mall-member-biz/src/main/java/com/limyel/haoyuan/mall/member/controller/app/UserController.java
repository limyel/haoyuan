package com.limyel.haoyuan.mall.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/me")
    public R<UserInfoVO> getCurrentUserInfo() {
        // todo
        return R.ok(null);
    }

}
