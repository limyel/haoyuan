package com.limyel.haoyuan.module.system.auth.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.common.util.TokenUtil;
import com.limyel.haoyuan.module.system.auth.dao.UserTokenDao;
import com.limyel.haoyuan.module.system.auth.dataobject.LoginUser;
import com.limyel.haoyuan.module.system.auth.dto.LoginDTO;
import com.limyel.haoyuan.module.system.auth.service.AuthService;
import com.limyel.haoyuan.module.system.auth.service.UserTokenService;
import com.limyel.haoyuan.module.system.auth.vo.LoginVO;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public LoginVO login(LoginDTO dto) {
        // AuthenticationManager 用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证失败
        if (authenticate == null) {
            throw new BizException();
        }

        // 认证通过
        // todo 缓存
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return userTokenService.generateToken(loginUser.getSysUser().getId());
    }

}
