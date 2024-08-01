package com.limyel.haoyuan.blog.admin.controller.admin;

import com.limyel.haoyuan.blog.admin.constant.AdminErrorMsg;
import com.limyel.haoyuan.blog.admin.dto.user.UpdatePasswordDTO;
import com.limyel.haoyuan.blog.admin.service.UserService;
import com.limyel.haoyuan.blog.admin.vo.UserInfoVO;
import com.limyel.haoyuan.common.core.pojo.R;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public R<?> create() {
        return R.ok();
    }

    @GetMapping("/get/current-user-info")
    public R<?> getCurrentUserInfo() {
        UserInfoVO result = userService.getCurrentUserInfo();
        return R.ok(result);
    }

    @PostMapping("/update/password")
    public R<?> updatePassword(@RequestBody @Validated UpdatePasswordDTO dto) {
        int result = userService.updatePassword(dto);
        return result == 1 ? R.ok() : R.failed(AdminErrorMsg.USER_NOT_FOUND);
    }


}
