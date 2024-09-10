package com.limyel.haoyuan.mallcloud.auth.token;

import com.limyel.haoyuan.mallcloud.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mallcloud.auth.entity.MemberUserDetails;
import com.limyel.haoyuan.mallcloud.auth.entity.SysUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt token 增强
 */
public class JwtTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MallUserDetails loginUser = (MallUserDetails) authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> additionalInformation = new HashMap<>(4);
        if (loginUser instanceof SysUserDetails) {
            additionalInformation.put("sysUserId", loginUser.getId());
        } else if (loginUser instanceof MemberUserDetails) {
            additionalInformation.put("memberUserId", loginUser.getId());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(accessToken, authentication);
    }
}
