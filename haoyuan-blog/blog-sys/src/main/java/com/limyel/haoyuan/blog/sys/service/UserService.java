package com.limyel.haoyuan.blog.sys.service;

import com.limyel.haoyuan.blog.sys.dao.SysUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SysUserDao sysUserDao;

    private final PasswordEncoder passwordEncoder;

}
