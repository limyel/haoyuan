package com.limyel.haoyuan.mall.common.auth.service;

import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MallUserDetailsService extends UserDetailsService {

    MallUserDetails loadUserByMobile(String mobile);

}
