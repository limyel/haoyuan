package com.limyel.haoyuan.mall.security.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface MallUserDetails extends UserDetails {

    Long getId();

}
