package com.limyel.haoyuan.mallcloud.auth.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface MallUserDetails extends UserDetails {

    Long getId();

}
