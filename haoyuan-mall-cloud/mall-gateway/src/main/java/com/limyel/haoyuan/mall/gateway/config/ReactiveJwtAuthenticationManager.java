package com.limyel.haoyuan.mall.gateway.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ReactiveJwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken -> {
                    log.info("accessToken: {}", accessToken);
                    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    if (oAuth2AccessToken == null) {
                        return Mono.error(new InvalidTokenException("access_token 错误。"));
                    } else if (oAuth2AccessToken.isExpired()) {
                        return Mono.error(new InvalidTokenException("access_token 已过期。"));
                    }
                    OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
                    if (oAuth2Authentication == null) {
                        return Mono.error(new InvalidTokenException("access_token 无效。"));
                    } else {
                        return Mono.just(oAuth2Authentication);
                    }
                })).cast(Authentication.class);
    }
}
