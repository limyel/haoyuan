package com.limyel.haoyuan.mallcloud.auth.service;

import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.sys.api.SysUserFeignClient;
import com.limyel.haoyuan.mallcloud.auth.entity.SysUserDetails;
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
