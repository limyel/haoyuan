package com.limyel.haoyuan.system.constant;


import com.limyel.haoyuan.common.core.exception.ErrorCode;

public interface SysErrorCode {

    ErrorCode DEPT_NOT_FOUND = new ErrorCode(1_01_01_000, "部门不存在");
    ErrorCode DEPT_PARENT_ERROR = new ErrorCode(1_01_01_001, "不能设置自己为上级部门");
    ErrorCode DEPT_PARENT_NOT_FOUND = new ErrorCode(1_01_01_002, "上级部门不存在");
    ErrorCode DEPT_PARENT_IS_CHILD = new ErrorCode(1_01_01_003, "上级部门不能为子部门");
    ErrorCode DEPT_NAME_DUPLICATE = new ErrorCode(1_01_01_004, "部门名称重复");
    ErrorCode DEPT_HAS_CHILDREN = new ErrorCode(1_01_01_005, "当前部门存在子部门");

    ErrorCode POST_NOT_FOUND = new ErrorCode(1_01_02_000, "岗位不存在");
    ErrorCode POST_NAME_DUPLICATE = new ErrorCode(1_01_02_001, "岗位名称重复");
    ErrorCode POST_CODE_DUPLICATE = new ErrorCode(1_01_02_002, "岗位编码重复");

    ErrorCode USER_NOT_FOUND = new ErrorCode(1_01_03_000, "用户不存在");
    ErrorCode USER_USERNAME_DUPLICATE = new ErrorCode(1_01_03_001, "用户名重复");
    ErrorCode USER_MOBILE_DUPLICATE = new ErrorCode(1_01_03_002, "用户手机号重复");
    ErrorCode USER_EMAIL_DUPLICATE = new ErrorCode(1_01_03_003, "用户邮箱重复");

    ErrorCode DICT_DATA_NOT_FOUND = new ErrorCode(1_01_04_000, "字典数据不存在");

    ErrorCode DICT_DATA_VALUE_DUPLICATE = new ErrorCode(1_01_05_000, "字典值重复");

    ErrorCode DICT_TYPE_NOT_FOUND = new ErrorCode(1_01_06_000, "字典类型不存在");
    ErrorCode DICT_TYPE_NOT_ENABLE = new ErrorCode(1_01_06_001, "字典类型未开启");
    ErrorCode DICT_TYPE_TYPE_DUPLICATE = new ErrorCode(1_01_06_002, "字典类型重复");
    ErrorCode DICT_TYPE_NAME_DUPLICATE = new ErrorCode(1_01_06_003, "字典类型名称重复");

    ErrorCode MENU_NOT_FOUND = new ErrorCode(1_01_07_000, "菜单不存在");
    ErrorCode MENU_PID_ERROR = new ErrorCode(1_01_07_001, "不能设置自己为父菜单");
    ErrorCode MENU_PID_NOT_FOUND = new ErrorCode(1_01_07_002, "父菜单不存在");
    ErrorCode MENU_PID_NOT_DIR_OR_MENU = new ErrorCode(1_01_07_003, "父菜单的类型必须是目录或菜单");
    ErrorCode MENU_NAME_DUPLICATE = new ErrorCode(1_01_07_004, "菜单名称重复");

    ErrorCode ROLE_NOT_FOUND = new ErrorCode(1_01_08_000, "角色不存在");

    ErrorCode PARAM_NOT_FOUND = new ErrorCode(1_01_09_000, "参数不存在");


}