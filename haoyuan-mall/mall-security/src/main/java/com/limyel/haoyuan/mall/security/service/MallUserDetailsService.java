package com.limyel.haoyuan.mall.security.service;

import com.limyel.haoyuan.mall.security.entity.MallUserDetails;
import com.limyel.haoyuan.mall.security.exception.MobileNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MallUserDetailsService extends UserDetailsService {

    /**
     * 根据手机号检索用户
     *
     * @param mobile 手机号
     * @return 用户信息
     * @throws MobileNotFoundException 如果用户不存在，需要抛出 AuthenticationException
     */
    MallUserDetails loadUserByMobile(String mobile) throws MobileNotFoundException;

}
