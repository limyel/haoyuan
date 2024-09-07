package com.limyel.haoyuan.mallcloud.auth.service;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.dto.user.MemberUserInfo;
import com.limyel.haoyuan.mallcloud.auth.entity.MemberUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final UserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberUserInfo userInfo = userApi.getByUsername(username);
        MemberUserDetails result = new MemberUserDetails();
        result.setUserId(userInfo.getId());
        result.setUsername(userInfo.getUsername());
        result.setPassword(userInfo.getPassword());
        return result;
    }
}
