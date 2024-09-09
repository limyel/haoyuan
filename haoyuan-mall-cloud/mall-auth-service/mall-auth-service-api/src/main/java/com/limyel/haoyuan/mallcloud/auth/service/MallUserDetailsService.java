package com.limyel.haoyuan.mallcloud.auth.service;

import com.limyel.haoyuan.mallcloud.auth.entity.MallUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MallUserDetailsService extends UserDetailsService {

    MallUserDetails loadUserByMobile(String mobile);

}
