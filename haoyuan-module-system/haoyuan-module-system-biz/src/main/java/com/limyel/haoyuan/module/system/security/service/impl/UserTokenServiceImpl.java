package com.limyel.haoyuan.module.system.security.service.impl;

import com.limyel.haoyuan.common.exception.BizException;
import com.limyel.haoyuan.common.util.TokenUtil;
import com.limyel.haoyuan.module.system.security.dao.UserTokenDao;
import com.limyel.haoyuan.module.system.security.dataobject.LoginUser;
import com.limyel.haoyuan.module.system.security.dataobject.UserTokenDO;
import com.limyel.haoyuan.module.system.security.dto.LoginDTO;
import com.limyel.haoyuan.module.system.security.service.UserTokenService;
import com.limyel.haoyuan.module.system.security.vo.LoginVO;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        return generateToken(loginUser.getSysUser().getId());
    }

    @Override
    public void logout(Long userId) {
        userTokenDao.selectByUserId(userId);
        String token = TokenUtil.generateValue();

    }

    @Override
    public LoginVO generateToken(Long userId) {
        String token;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plusSeconds(3600 * 12);

        UserTokenDO userToken = userTokenDao.selectByUserId(userId);
        if (userToken == null) {
            token = TokenUtil.generateValue();

            userToken = new UserTokenDO();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);

            userTokenDao.insert(userToken);
        } else {
            if (now.isAfter(userToken.getExpireTime())) {
                // token 过期
                token = TokenUtil.generateValue();
            } else {
                token = userToken.getToken();
            }

            // 更新 token
            userToken.setToken(token);
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expireTime);
            userTokenDao.updateById(userToken);
        }

        LoginVO result = new LoginVO();
        result.setToken(token);
        return result;
    }

    @Override
    public Long getUserIdByToken(String token) {
        UserTokenDO userToken = userTokenDao.selectByToken(token);
        validateExpire(userToken);
        return userToken.getUserId();
    }

    @Override
    public SysUserDO getUserByToken(String token) {
        Long userId = getUserIdByToken(token);
        return sysUserService.get(userId);
    }

    private void validateExpire(UserTokenDO userToken) {
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new BizException();
        }
    }
}
