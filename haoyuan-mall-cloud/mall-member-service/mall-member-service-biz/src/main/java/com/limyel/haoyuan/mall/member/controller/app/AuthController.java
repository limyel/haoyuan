package com.limyel.haoyuan.mall.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.member.dto.auth.LoginDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import com.limyel.haoyuan.mall.member.vo.auth.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userAuthController")
@RequestMapping("user-auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO dto) {
        LoginVO result = userService.login(dto);
        return R.ok(result);
    }

}
