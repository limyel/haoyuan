package com.limyel.haoyuan.common.core.exception.code;

public class ResourceNotFoundErrorCode extends ErrorCode {

    private static final String PREFIX = "ResourceNotFound.";

    public static final ErrorCode ERROR_CODE = new ErrorCode("ResourceNotFound", "资源不存在");

    public ResourceNotFoundErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
