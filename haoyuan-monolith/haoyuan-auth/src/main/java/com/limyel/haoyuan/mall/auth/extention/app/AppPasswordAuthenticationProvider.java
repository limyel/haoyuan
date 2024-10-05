package com.limyel.haoyuan.mall.auth.extention.app;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.auth.service.MemberUserDetailsService;
import com.limyel.haoyuan.mall.common.auth.entity.UnLoginUser;
import com.limyel.haoyuan.mall.common.auth.extention.app.AppPasswordAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppPasswordAuthenticationProvider implements AuthenticationProvider {

    private final MemberUserDetailsService memberUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AppPasswordAuthenticationToken authenticationToken = (AppPasswordAuthenticationToken) authentication;

        UnLoginUser unLoginUser = (UnLoginUser) authenticationToken.getPrincipal();
        UserDetails userDetails = memberUserDetailsService.loadUserByUsername(unLoginUser.getUsername());
        if (!passwordEncoder.matches(unLoginUser.getPassword(), userDetails.getPassword())) {
            throw new ServiceException("账号密码错误。");
        }

        return new AppPasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
