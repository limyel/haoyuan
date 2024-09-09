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

    /**
     * 账户过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 锁定账户
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账户凭据（密码）过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 禁用账户
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
