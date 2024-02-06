package com.limyel.haoyuan.module.system.auth.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.module.system.auth.dataobject.LoginUser;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new BizException();
        }

        LoginUser loginUser = new LoginUser(sysUser);
        return loginUser;
    }

}
