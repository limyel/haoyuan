package com.limyel.haoyuan.cloud.auth.entity;


import org.springframework.security.core.userdetails.UserDetails;

/**
 * 表示 SysUser 或者 MemberUser 的信息，用于在已认证的 Authentication 中传递
 */
public interface MallUserDetails extends UserDetails {

    Long getId();

}
