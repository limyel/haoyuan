package com.limyel.haoyuan.common.core.exception.code;

public class ResourceNotFoundErrorCode extends ErrorCode {

    private static final String PREFIX = "ResourceNotFound.";

    public ResourceNotFoundErrorCode(String code, String msg) {
        super(PREFIX + code, msg);
    }

}
