package com.limyel.haoyuan.mall.auth.controller;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.auth.constant.GrantTypeEnum;
import com.limyel.haoyuan.mall.common.auth.dto.LoginDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public R<?> login(@Validated @RequestBody LoginDTO dto) {
        Optional<GrantTypeEnum> grantTypeOptional = GrantTypeEnum.from(dto.getGrantType());
        if (grantTypeOptional.isEmpty()) {
            throw new ServiceException("不支持该认证模式");
        }

        GrantTypeEnum grantType = grantTypeOptional.get();

        return null;
    }

}
