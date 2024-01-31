package com.limyel.haoyuan.module.system.exception;

import com.limyel.haoyuan.common.exception.ErrorCode;

public interface SysErrorCode {

    ErrorCode DEPT_NOT_FOUND = new ErrorCode(1_02_01000, "部门不存在");
    ErrorCode DEPT_PARENT_ERROR = new ErrorCode(1_02_01001, "不能设置自己为上级部门");
    ErrorCode DEPT_PARENT_NOT_FOUND = new ErrorCode(1_02_01002, "上级部门不存在");
    ErrorCode DEPT_PARENT_IS_CHILD = new ErrorCode(1_02_01003, "上级部门不能为子部门");
    ErrorCode DEPT_NAME_DUPLICATE = new ErrorCode(1_02_01004, "部门名称重复");
    ErrorCode DEPT_HAS_CHILDREN = new ErrorCode(1_02_01005, "当前部门存在子部门");

    ErrorCode POST_NOT_FOUND = new ErrorCode(1_02_02000, "岗位不存在");
    ErrorCode POST_NAME_DUPLICATE = new ErrorCode(1_02_02001, "岗位名称重复");
    ErrorCode POST_CODE_DUPLICATE = new ErrorCode(1_02_02002, "岗位编码重复");

    ErrorCode USER_NOT_FOUND = new ErrorCode(1_02_03000, "用户不存在");
    ErrorCode USER_USERNAME_DUPLICATE = new ErrorCode(1_02_03001, "用户名重复");
    ErrorCode USER_MOBILE_DUPLICATE = new ErrorCode(1_02_03002, "用户手机号重复");
    ErrorCode USER_EMAIL_DUPLICATE = new ErrorCode(1_02_03003, "用户邮箱重复");

    ErrorCode DICT_DATA_NOT_FOUND = new ErrorCode(1_02_04000, "字典数据不存在");

    ErrorCode DICT_TYPE_NOT_FOUND = new ErrorCode(1_02_05000, "字典类型不存在");
    ErrorCode DICT_TYPE_NOT_ENABLE = new ErrorCode(1_02_05001, "字典类型未开启");
    ErrorCode DICT_TYPE_VALUE_DUPLICATE = new ErrorCode(1_02_05002, "字典值重复");

}
