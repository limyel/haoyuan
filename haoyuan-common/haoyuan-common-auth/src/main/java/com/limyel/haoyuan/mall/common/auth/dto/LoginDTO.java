package com.limyel.haoyuan.mall.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(title = "认证 DTO")
public class LoginDTO {

    @Schema(title = "用户名", description = "以下认证模式必填：密码模式、app密码模式、图形验证码模式", defaultValue = "zhu_ge_liang")
    private String username;

    @Schema(title = "密码", description = "以下认证模式必填：密码模式、app密码模式", defaultValue = "123456")
    private String password;

    @Schema(title = "手机号", description = "以下认证模式必填：短信验证码模式")
    private String mobile;

    @Schema(title = "短信验证码", description = "以下认证模式必填：短信验证码模式")
    private String smsCode;

    @NotBlank(message = "认证类型不能为空。")
    @Schema(title = "认证类型", description = "密码模式：password，app密码模式：app_password，短信验证码模式：sms_code，" +
            "图形验证码模式：captcha，刷新访问令牌：refresh_token", defaultValue = "password")
    private String grantType;

}
