package com.limyel.haoyuan.common.core.exception.code;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;

public interface GlobalErrorCode {

    ErrorCode SUCCESS = new ErrorCode("Success", "成功");
    ErrorCode INTERNAL_ERROR = new ErrorCode("InternalError", "内部错误");
    ErrorCode INVALID_PARAMETER = new ErrorCode("InvalidParameter", "参数错误");
    ErrorCode AUTH_FAILURE = new ErrorCode("AuthFailure", "认证/授权错误");
    ErrorCode RESOURCE_NOT_FOUND = new ErrorCode("ResourceNotFound", "资源不存在");
    ErrorCode FAILED_OPERATION = new ErrorCode("FailedOperation", "操作失败");

}