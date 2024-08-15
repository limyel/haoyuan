package com.limyel.haoyuan.mall.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.satoken.annotation.SaUserCheckLogin;
import com.limyel.haoyuan.mall.member.rdto.user.UserInfoRDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import com.limyel.haoyuan.mall.member.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaUserCheckLogin
    @GetMapping("/get/me")
    public R<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO result = userService.getCurrentUserInfo();
        return R.ok(result);
    }

    // todo 做成黑名单
    @GetMapping("/get/by-username/{username}")
    public R<UserInfoRDTO> getByUsername(@PathVariable("username") String username) {
        UserInfoRDTO result = userService.getByUsername(username);
        return R.ok(result);
    }

}
