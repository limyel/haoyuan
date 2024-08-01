package com.limyel.haoyuan.blog.sys.controller.sys;

import com.limyel.haoyuan.blog.sys.constant.AdminErrorMsg;
import com.limyel.haoyuan.blog.sys.domain.UserEntity;
import com.limyel.haoyuan.blog.sys.dto.user.UpdatePasswordDTO;
import com.limyel.haoyuan.blog.sys.dto.user.UserDTO;
import com.limyel.haoyuan.blog.sys.service.UserService;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "Admin 用户模块")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody UserDTO dto) {
        userService.create(dto);
        return R.ok();
    }

    @GetMapping("/get/current-user-info")
    public R<?> getCurrentUserInfo() {
        UserEntity user = userService.getCurrentUser();
        return R.ok(user);
    }

    @PostMapping("/update/password")
    public R<?> updatePassword(@RequestBody @Validated UpdatePasswordDTO dto) {
        int result = userService.updatePassword(dto);
        return result == 1 ? R.ok() : R.failed(AdminErrorMsg.USER_NOT_FOUND);
    }


}
