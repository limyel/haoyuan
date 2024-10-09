package com.limyel.haoyuan.cloud.auth.service;

import com.limyel.haoyuan.cloud.auth.entity.MallUserDetails;
import com.limyel.haoyuan.cloud.auth.entity.SysUserDetails;
import com.limyel.haoyuan.cloud.sys.api.SysUserFeignClient;
import com.limyel.haoyuan.cloud.sys.dto.SysUserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserFeignClient sysUserFeignClient;

    @Override
    public MallUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserSecurity sysUser = sysUserFeignClient.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new SysUserDetails(sysUser);
    }
}
