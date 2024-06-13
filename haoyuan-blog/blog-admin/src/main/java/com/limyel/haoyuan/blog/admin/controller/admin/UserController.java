package com.limyel.haoyuan.blog.admin.controller.admin;

import com.limyel.haoyuan.blog.admin.service.UserService;
import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R<?> create() {
        return R.ok();
    }

}
