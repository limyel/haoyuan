package com.limyel.haoyuan.cloud.auth.service;

import com.limyel.haoyuan.cloud.auth.entity.MallUserDetails;
import com.limyel.haoyuan.cloud.auth.entity.MemberUserDetails;
import com.limyel.haoyuan.cloud.member.api.UserFeignClient;
import com.limyel.haoyuan.cloud.member.dto.MemberUserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class MemberUserDetailsService implements MallUserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberUserSecurity userInfo = userFeignClient.getByUsername(username);
        return buildUserDetails(userInfo);
    }

    @Override
    public MallUserDetails loadUserByMobile(String mobile) {
        MemberUserSecurity userInfo = userFeignClient.getByMobile(mobile);
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
