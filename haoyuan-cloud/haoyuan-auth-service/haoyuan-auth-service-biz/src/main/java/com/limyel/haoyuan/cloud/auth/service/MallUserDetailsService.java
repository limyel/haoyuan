package com.limyel.haoyuan.cloud.auth.service;

import com.limyel.haoyuan.cloud.auth.entity.MallUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MallUserDetailsService extends UserDetailsService {

    MallUserDetails loadUserByMobile(String mobile);

}
