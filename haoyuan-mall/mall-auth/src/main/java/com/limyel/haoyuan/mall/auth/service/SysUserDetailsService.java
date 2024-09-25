package com.limyel.haoyuan.mall.auth.service;

import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.SysUserDetails;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserApi sysUserApi;

    @Override
    public MallUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserSecurity sysUser = sysUserApi.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new SysUserDetails(sysUser);
    }
}
