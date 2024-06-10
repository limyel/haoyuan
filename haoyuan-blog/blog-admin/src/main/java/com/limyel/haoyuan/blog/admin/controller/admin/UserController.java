package com.limyel.haoyuan.blog.admin.controller.admin;

import com.limyel.haoyuan.blog.admin.service.UserService;
import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public R<?> create() {
        return R.ok();
    }

}
