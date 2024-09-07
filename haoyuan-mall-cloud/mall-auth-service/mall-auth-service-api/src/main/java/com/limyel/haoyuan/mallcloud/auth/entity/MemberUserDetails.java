package com.limyel.haoyuan.mallcloud.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class MemberUserDetails implements MallUserDetails {

    private Long userId;

    private String username;

    private String password;

    public MemberUserDetails() {
    }

    public MemberUserDetails(Long id, String username, String password) {
        this.userId = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
