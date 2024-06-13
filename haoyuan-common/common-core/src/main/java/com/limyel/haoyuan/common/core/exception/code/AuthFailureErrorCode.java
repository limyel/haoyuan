package com.limyel.haoyuan.common.core.exception.code;

public class AuthFailureErrorCode extends ErrorCode {

    private static final String PREFIX = "AuthFailure.";

    public static final ErrorCode ERROR_CODE = new ErrorCode("AuthFailure", "认证/授权错误");

    public static final ErrorCode FORBIDDEN = new AuthFailureErrorCode("Forbidden", "该账号无此操作权限");
    public static final ErrorCode AUTH_FAILED = new AuthFailureErrorCode("AuthFailed", "认证失败，用户名或密码错误");
    public static final ErrorCode USERNAME_OR_PASSWORD_NULL = new AuthFailureErrorCode("UsernameOrPasswordNull", "用户名或密码为空");


    public AuthFailureErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
