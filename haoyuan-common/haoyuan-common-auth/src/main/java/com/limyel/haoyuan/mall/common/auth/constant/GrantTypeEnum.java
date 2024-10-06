package com.limyel.haoyuan.mall.common.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum GrantTypeEnum {
    PASSWORD("密码模式", "password"),
    APP_PASSWORD("app密码模式", "app_password"),
    SMS_CODE("短信验证码模式", "sms_code"),
    CAPTCHA("图形验证码模式", "captcha"),
    REFRESH_TOKEN("刷新访问令牌", "refresh_token")
    ;

    private final String label;
    private final String value;

    public static Optional<GrantTypeEnum> from(String grantType) {
        return Arrays.stream(GrantTypeEnum.values())
                .filter(item -> item.value.equals(grantType))
                .findFirst();
    }

}
