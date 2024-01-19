package com.limyel.haoyuan.module.system.constant;

import com.limyel.haoyuan.common.exception.ErrorCode;

public interface SysErrorCodeConstant {

    ErrorCode DEPT_PARENT_ERROR = new ErrorCode(1_02_01000, "不能设置自己为上级部门");
    ErrorCode DEPT_PARENT_NOT_FOUND = new ErrorCode(1_02_01001, "上级部门不存在");
    ErrorCode DEPT_PARENT_IS_CHILD = new ErrorCode(1_02_01002, "上级部门不能为子部门");
    ErrorCode DEPT_NAME_DUPLICATE = new ErrorCode(1_02_01003, "部门名称重复");
    ErrorCode DEPT_NOT_FOUND = new ErrorCode(1_02_01004, "部门不存在");
    ErrorCode DEPT_HAS_CHILDREN = new ErrorCode(1_02_01005, "当前部门存在子部门");

    ErrorCode POST_NAME_DUPLICATE = new ErrorCode(1_02_02000, "岗位名称重复");
    ErrorCode POST_CODE_DUPLICATE = new ErrorCode(1_02_02001, "岗位编码重复");
    ErrorCode POST_NOT_FOUND = new ErrorCode(1_02_02002, "岗位不存在");

}
