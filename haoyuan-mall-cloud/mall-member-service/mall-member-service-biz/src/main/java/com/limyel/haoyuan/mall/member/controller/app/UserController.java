package com.limyel.haoyuan.mall.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void create() {

    }

    @GetMapping("/get/me")
    public void getCurrentUserInfo() {

    }

    @GetMapping("/get/by-username/{username}")
    public R<UserInfoDTO> getByUsername(@PathVariable("username") String username) {
        UserInfoDTO result = userService.getByUsername(username);
        return R.ok(result);
    }

}
