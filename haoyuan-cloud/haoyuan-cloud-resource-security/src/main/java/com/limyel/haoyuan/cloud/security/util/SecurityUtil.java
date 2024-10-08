package com.limyel.haoyuan.cloud.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class SecurityUtil {

    public static Long getSysUserId() {
        return Long.valueOf(String.valueOf(getTokenAttributes().get("sysUserId")));
    }

    public static Optional<Long> getMemberUserId() {
        Object memberUserId = getTokenAttributes().get("memberUserId");
        if (memberUserId == null) {
            return Optional.empty();
        }
        return Optional.of(Long.valueOf(String.valueOf(memberUserId)));
    }

    public static Map<String, Object> getTokenAttributes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof OAuth2Authentication oAuth2Authentication) {
                Object details = oAuth2Authentication.getDetails();
                if (details instanceof OAuth2AuthenticationDetails oAuth2AuthenticationDetails) {
                    return (Map<String, Object>) oAuth2AuthenticationDetails.getDecodedDetails();
                }
            }
        }
        return Collections.emptyMap();
    }

}
