package com.limyel.haoyuan.mallcloud.auth.service;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.member.dto.user.MemberUserSecurity;
import com.limyel.haoyuan.mallcloud.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mallcloud.auth.entity.MemberUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class MemberUserDetailsService implements MallUserDetailsService {

    private final UserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberUserSecurity userInfo = userApi.getByUsername(username);
        return buildUserDetails(userInfo);
    }

    @Override
    public MallUserDetails loadUserByMobile(String mobile) {
        MemberUserSecurity userInfo = userApi.getByMobile(mobile);
        return buildUserDetails(userInfo);
    }

    private MemberUserDetails buildUserDetails(MemberUserSecurity userInfo) {
        MemberUserDetails result = new MemberUserDetails();
        result.setUserId(userInfo.getId());
        result.setUsername(userInfo.getUsername());
        result.setPassword(userInfo.getPassword());
        return result;
    }
}
