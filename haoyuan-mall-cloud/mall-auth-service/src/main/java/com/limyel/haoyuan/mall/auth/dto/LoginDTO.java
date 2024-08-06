package com.limyel.haoyuan.mall.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "系统类型不能为空")
    private String sysType;

    @NotBlank(message = "登录类型不能为空")
    private String loginType;

    private String authInfo;

    private String code;

    private String uuid;

}
