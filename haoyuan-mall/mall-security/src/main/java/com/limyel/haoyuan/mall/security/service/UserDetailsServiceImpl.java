package com.limyel.haoyuan.mall.security.service;

import com.limyel.haoyuan.mall.security.entity.LoginUser;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserApi sysUserApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserSecurity sysUser = sysUserApi.getByUsername(username);

        return new LoginUser(sysUser, Arrays.asList("admin"));
    }

}
