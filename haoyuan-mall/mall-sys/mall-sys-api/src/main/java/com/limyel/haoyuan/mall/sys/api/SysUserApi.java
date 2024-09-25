package com.limyel.haoyuan.mall.sys.api;


import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;

public interface SysUserApi {

    SysUserSecurity getByUsername(String username);

}
