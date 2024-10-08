package com.limyel.haoyuan.cloud.auth.token;

import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.mall.common.auth.entity.MallUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.MemberUserDetails;
import com.limyel.haoyuan.mall.common.auth.entity.SysUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt token 增强
 */
@RequiredArgsConstructor
public class MallJwtTokenConverter extends JwtAccessTokenConverter {

    private final StringRedisTemplate redisTemplate;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MallUserDetails userDetails = (MallUserDetails) authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> additionalInformation = new HashMap<>(4);
        if (userDetails instanceof SysUserDetails sysUserDetails) {
            additionalInformation.put("sysUserId", userDetails.getId());
            additionalInformation.put("authorities", Collections.emptyList());

            redisTemplate.opsForValue().set("AUTH:SYS_USER_PERMS:" + sysUserDetails.getId(), JSONUtil.toJson(sysUserDetails.getPerms()));
        } else if (userDetails instanceof MemberUserDetails) {
            additionalInformation.put("memberUserId", userDetails.getId());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return super.enhance(accessToken, authentication);
    }
}
