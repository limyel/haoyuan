package com.limyel.haoyuan.system.constant;


import com.limyel.haoyuan.common.core.exception.code.ErrorCode;
import com.limyel.haoyuan.common.core.exception.code.FailedOperationErrorCode;
import com.limyel.haoyuan.common.core.exception.code.InvalidParameterErrorCode;
import com.limyel.haoyuan.common.core.exception.code.ResourceNotFoundErrorCode;

public interface SysErrorCode {

    ErrorCode DEPT_NOT_FOUND = new ResourceNotFoundErrorCode("Sys.DeptNotFound", "部门不存在");
    ErrorCode DEPT_PARENT_ERROR = new InvalidParameterErrorCode("Sys.DeptParentError", "不能设置自己为上级部门");
    ErrorCode DEPT_PARENT_NOT_FOUND = new ResourceNotFoundErrorCode("Sys.DeptParentNotFound", "上级部门不存在");
    ErrorCode DEPT_PARENT_IS_CHILD = new InvalidParameterErrorCode("Sys.DeptParentIsChild", "上级部门不能为子部门");
    ErrorCode DEPT_NAME_DUPLICATE = new InvalidParameterErrorCode("Sys.DeptNameDuplicate", "部门名称重复");
    ErrorCode DEPT_HAS_CHILDREN = new FailedOperationErrorCode("Sys.DeptHasChidren", "当前部门存在子部门");

    ErrorCode POST_NOT_FOUND = new ResourceNotFoundErrorCode("Sys.PostNotFound", "岗位不存在");
    ErrorCode POST_NAME_DUPLICATE = new InvalidParameterErrorCode("Sys.PostNameDuplicate", "岗位名称重复");
    ErrorCode POST_CODE_DUPLICATE = new InvalidParameterErrorCode("Sys.PostCodeDuplicate", "岗位编码重复");

    ErrorCode USER_NOT_FOUND = new ErrorCode("", "用户不存在");
    ErrorCode USER_USERNAME_DUPLICATE = new ErrorCode("", "用户名重复");
    ErrorCode USER_MOBILE_DUPLICATE = new ErrorCode("", "用户手机号重复");
    ErrorCode USER_EMAIL_DUPLICATE = new ErrorCode("", "用户邮箱重复");

    ErrorCode DICT_DATA_NOT_FOUND = new ErrorCode("", "字典数据不存在");

    ErrorCode DICT_DATA_VALUE_DUPLICATE = new ErrorCode("", "字典值重复");

    ErrorCode DICT_TYPE_NOT_FOUND = new ErrorCode("", "字典类型不存在");
    ErrorCode DICT_TYPE_NOT_ENABLE = new ErrorCode("", "字典类型未开启");
    ErrorCode DICT_TYPE_TYPE_DUPLICATE = new ErrorCode("", "字典类型重复");
    ErrorCode DICT_TYPE_NAME_DUPLICATE = new ErrorCode("", "字典类型名称重复");

    ErrorCode MENU_NOT_FOUND = new ErrorCode("", "菜单不存在");
    ErrorCode MENU_PID_ERROR = new ErrorCode("", "不能设置自己为父菜单");
    ErrorCode MENU_PID_NOT_FOUND = new ErrorCode("", "父菜单不存在");
    ErrorCode MENU_PID_NOT_DIR_OR_MENU = new ErrorCode("", "父菜单的类型必须是目录或菜单");
    ErrorCode MENU_NAME_DUPLICATE = new ErrorCode("", "菜单名称重复");

    ErrorCode ROLE_NOT_FOUND = new ErrorCode("", "角色不存在");

    ErrorCode PARAM_NOT_FOUND = new ErrorCode("", "参数不存在");


}