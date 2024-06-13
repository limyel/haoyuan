package com.limyel.haoyuan.blog.admin.exception;

import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ResourceNotFoundErrorCode;

public interface AdminErrorCode {

    ErrorCode UserNotFound = new ResourceNotFoundErrorCode("Admin.UserNotFound", "该用户不存在");

}
