package com.limyel.haoyuan.cloud.auth.extention.sms;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.common.auth.service.MallUserDetailsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 短信登录鉴权 Provider
 */
@Slf4j
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private MallUserDetailsService mallUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        checkSmsMobile(mobile);

        MallUserDetails userDetails = mallUserDetailsService.loadUserByMobile(mobile);
        if (userDetails == null) {
            throw new ServiceException("手机号错误。");
        }

        // 认证通过
        return new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void checkSmsMobile(String mobile) {

    }
}
