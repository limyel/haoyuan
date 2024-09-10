package com.limyel.haoyuan.mallcloud.auth.extention.app;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.mallcloud.auth.entity.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;

public class AppPasswordTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "app_password";

    private final AuthenticationManager authenticationManager;

    public AppPasswordTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                                   OAuth2RequestFactory requestFactory, AuthenticationManager authenticationManager) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        LoginUser user = new LoginUser();
        user.setUsername(parameters.get("username"));
        user.setPassword(parameters.get("password"));

        Authentication authentication = new AppPasswordAuthenticationToken(user);
        authentication = authenticationManager.authenticate(authentication);

        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2Request oAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(oAuth2Request, authentication);
        } else {
            throw new ServiceException("用户名或密码错误。");
        }
    }
}
