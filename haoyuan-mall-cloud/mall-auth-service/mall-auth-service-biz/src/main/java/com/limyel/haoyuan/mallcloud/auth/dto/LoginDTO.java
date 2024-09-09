package com.limyel.haoyuan.mallcloud.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    private String username;

    private String password;

    private String mobile;

    private String smsCode;

    @NotBlank(message = "认证类型不能为空。")
    private String grantType;

}
