package com.limyel.haoyuan.mall.gateway.auth;

import com.alibaba.nacos.common.utils.ConcurrentHashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private Set<String> permitAll = new ConcurrentHashSet<>();
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AccessManager() {
        permitAll.add("/**/oauth/**");
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        //请求资源
        String requestPath = exchange.getRequest().getURI().getPath();

        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }

        List<String> authorities = Arrays.asList("ADMIN", "CUSTOMER");

//        mono
//                .filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authorities::contains)
//

        return mono
                .doOnNext(auth -> System.out.println("Received auth: " + auth))
                .doOnSubscribe(sub -> System.out.println("Subscribed"))
                .map(auth -> new AuthorizationDecision(checkAuthorities(exchange, auth, requestPath)))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    private boolean permitAll(String requestPath) {
        return permitAll.stream()
                .filter(r -> antPathMatcher.match(r, requestPath)).findFirst().isPresent();
    }

    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        System.out.println(auth);
        if(auth instanceof OAuth2Authentication){
            OAuth2Authentication athentication = (OAuth2Authentication) auth;
            String clientId = athentication.getOAuth2Request().getClientId();
            log.info("clientId is {}",clientId);
        }

        Object principal = auth.getPrincipal();
        log.info("用户信息:{}",principal.toString());
        return true;
    }
}
