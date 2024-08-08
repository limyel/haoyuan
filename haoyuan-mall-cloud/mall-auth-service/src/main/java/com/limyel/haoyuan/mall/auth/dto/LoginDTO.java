package com.limyel.haoyuan.mall.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "登录类型不能为空")
    private String loginType;

    @NotBlank(message = "认证方式不能为空")
    private String authType;

    private String authInfo;

    private String code;

    private String uuid;

}
