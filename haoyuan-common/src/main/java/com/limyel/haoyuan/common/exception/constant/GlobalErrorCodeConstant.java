package com.limyel.haoyuan.common.exception.constant;

import com.limyel.haoyuan.common.exception.ErrorCode;

public interface GlobalErrorCodeConstant {

    // ========== 客户端错误段 ==========

    ErrorCode SUCCESS = new ErrorCode(0, "成功");

    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");

    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");

    ErrorCode NOT_FOUND = new ErrorCode(404, "资源不存在");

    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");

    // ========== 自定义错误段 ==========

    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");

}
