package com.limyel.haoyuan.mall.sys.api;

import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfo;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;

public interface SysUserApi {

    SysUserSecurity getByUsername(String username);

    SysUserInfo getById(Long id);

}
