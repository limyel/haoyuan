package com.limyel.haoyuan.mall.member.controller.app;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.mall.member.dto.user.UserDTO;
import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import com.limyel.haoyuan.mall.member.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody UserDTO dto) {
        userService.create(dto);
        return R.ok();
    }

    @SaCheckLogin
    @GetMapping("/get/me")
    public R<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO result = userService.getCurrentUserInfo();
        return R.ok(result);
    }

    // todo 做成黑名单
    @GetMapping("/get/by-username/{username}")
    public R<UserInfoDTO> getByUsername(@PathVariable("username") String username) {
        UserInfoDTO result = userService.getByUsername(username);
        return R.ok(result);
    }

}
