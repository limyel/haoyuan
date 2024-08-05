package com.limyel.haoyuan.mall.member.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminUserController")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/delete/{ids}")
    public void delete(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update/status")
    public void updateStatus() {

    }

    @PostMapping("/update/blog-admin")
    public void updateBlogAdmin() {

    }

    @GetMapping("/get/page")
    public void getPage() {

    }

}
