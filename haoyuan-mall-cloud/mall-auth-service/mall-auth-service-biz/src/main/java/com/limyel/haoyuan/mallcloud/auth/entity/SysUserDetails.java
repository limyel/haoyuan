package com.limyel.haoyuan.mallcloud.auth.entity;

import com.limyel.haoyuan.common.core.constant.StatusEnum;
import com.limyel.haoyuan.mall.common.sys.dto.api.SysUserSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SysUserDetails implements MallUserDetails {

    private final SysUserSecurity sysUser;

    private Set<String> perms;

    private Set<? extends GrantedAuthority> authorities;

    public SysUserDetails(SysUserSecurity sysUser) {
        this.sysUser = sysUser;
        this.perms = sysUser.getPermissions();
    }

    public Set<String> getPerms() {
        return perms;
    }

    @Override
    public Long getId() {
        return sysUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = sysUser.getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return authorities;
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
