package com.limyel.haoyuan.mall.security.entity;

import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private List<String> permissions;

    private List<String> roles;

    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(SysUserSecurity sysUser, List<String> permissions) {
        this.id = sysUser.getId();
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
        return true;
    }
}
