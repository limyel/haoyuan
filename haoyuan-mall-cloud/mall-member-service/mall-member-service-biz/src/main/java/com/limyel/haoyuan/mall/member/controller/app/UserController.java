package com.limyel.haoyuan.mall.member.controller.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/create")
    public void create() {

    }

    @GetMapping("/get/me")
    public void getCurrentUserInfo() {

    }

}
