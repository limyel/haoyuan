package com.limyel.haoyuan.blog.sys.controller.admin;

import com.limyel.haoyuan.blog.sys.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "Admin 用户模块")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
