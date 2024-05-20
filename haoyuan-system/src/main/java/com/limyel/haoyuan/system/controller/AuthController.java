package com.limyel.haoyuan.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.core.exception.GlobalErrorCode;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.system.entity.SysUserEntity;
import com.limyel.haoyuan.system.satoken.LoginDTO;
import com.limyel.haoyuan.system.satoken.TokenVO;
import com.limyel.haoyuan.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public TokenVO login(@RequestBody LoginDTO dto) {
        SysUserEntity sysUser = sysUserService.getByUsername(dto.getUsername());
        if (!Objects.equals(dto.getPassword(), sysUser.getPassword())) {
            throw new BizException(GlobalErrorCode.UNAUTHORIZED_LOGIN_FAILED);
        }
        StpUtil.login(sysUser.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        TokenVO result = new TokenVO();
        result.setToken(tokenInfo.getTokenValue());
        return result;
    }

}
