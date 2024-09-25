package com.limyel.haoyuan.mall.auth.service;

import com.limyel.haoyuan.mall.common.auth.dto.MemberUserSecurity;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.MemberUserDetails;
import com.limyel.haoyuan.mall.common.auth.service.MallUserDetailsService;
import com.limyel.haoyuan.mall.member.api.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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
