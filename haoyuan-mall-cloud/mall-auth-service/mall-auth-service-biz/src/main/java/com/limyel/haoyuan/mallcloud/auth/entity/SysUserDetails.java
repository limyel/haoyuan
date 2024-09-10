package com.limyel.haoyuan.mallcloud.auth.entity;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class SysUserDetails implements MallUserDetails {

    private final SysUserSecurity sysUser;

    public SysUserDetails(SysUserSecurity sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Long getId() {
        return sysUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StatusEnum.ENABLE.getValue().equals(sysUser.getStatus());
    }
}
