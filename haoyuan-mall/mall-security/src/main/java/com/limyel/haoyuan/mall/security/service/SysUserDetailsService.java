package com.limyel.haoyuan.mall.security.service;

import com.limyel.haoyuan.mall.security.entity.MallUserDetails;
import com.limyel.haoyuan.mall.security.entity.SysUserDetails;
import com.limyel.haoyuan.mall.security.exception.MobileNotFoundException;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class SysUserDetailsService implements MallUserDetailsService {

    private final SysUserApi sysUserApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserSecurity sysUser = sysUserApi.getByUsername(username);
        return new SysUserDetails(sysUser);
    }

    @Override
    public MallUserDetails loadUserByMobile(String mobile) throws MobileNotFoundException {
        return null;
    }

}
