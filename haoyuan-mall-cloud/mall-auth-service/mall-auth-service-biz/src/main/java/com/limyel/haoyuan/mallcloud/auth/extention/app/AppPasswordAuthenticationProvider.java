package com.limyel.haoyuan.mallcloud.auth.extention.app;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mallcloud.auth.entity.LoginUser;
import com.limyel.haoyuan.mallcloud.auth.service.MemberUserDetailsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Data
public class AppPasswordAuthenticationProvider implements AuthenticationProvider {

    private MemberUserDetailsService memberUserDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AppPasswordAuthenticationToken authenticationToken = (AppPasswordAuthenticationToken) authentication;

        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        UserDetails userDetails = memberUserDetailsService.loadUserByUsername(loginUser.getUsername());
        if (!passwordEncoder.matches(loginUser.getPassword(), userDetails.getPassword())) {
            throw new ServiceException("账号密码错误。");
        }

        return new AppPasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
