package com.limyel.haoyuan.mall.sys.controller.admin;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.security.vo.LoginVO;
import com.limyel.haoyuan.mall.sys.dto.LoginDTO;
import com.limyel.haoyuan.mall.sys.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("sysAuthController")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO dto) {
        LoginVO result = authService.login(dto);
        return R.ok(result);
    }

}
