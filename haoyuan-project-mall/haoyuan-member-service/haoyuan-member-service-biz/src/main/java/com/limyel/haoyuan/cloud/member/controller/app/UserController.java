package com.limyel.haoyuan.cloud.member.controller.app;

import com.limyel.haoyuan.cloud.member.vo.user.UserInfoVO;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.cloud.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("@pms.isMemberAuthenticated()")
    @GetMapping("/get/me")
    public R<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO result = userService.getCurrentUserInfo();
        return R.ok(result);
    }

}
