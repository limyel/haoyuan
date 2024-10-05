package com.limyel.haoyuan.mallcloud.auth.config;

import com.limyel.haoyuan.mallcloud.auth.extention.app.AppPasswordTokenGranter;
import com.limyel.haoyuan.mallcloud.auth.extention.sms.SmsCodeTokenGranter;
import com.limyel.haoyuan.mallcloud.auth.service.SysUserDetailsService;
import com.limyel.haoyuan.mallcloud.auth.token.MallJwtTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;

    private final SysUserDetailsService sysUserDetailsService;

    private final StringRedisTemplate redisTemplate;

    /**
     * 帮助 jwt token 和 oauth 身份信息之间做转换
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new MallJwtTokenConverter(redisTemplate);
        // 对称密钥
        converter.setSigningKey("limyel");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        // 使用数据库存储 token
        // return new JdbcTokenStore(dataSource);

        // 使用 jwt token
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setClientDetailsService(jdbcClientDetailsService());
        tokenServices.setTokenEnhancer(jwtTokenEnhancer());
        // 这里设置的时间不一定会生效，还会去 client_details 里面查看 client 的时间配置
        // access_token 过期时间，默认 12 小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 6);
        // refresh_token 过期时间，默认 30 天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    /**
     * 认证服务器端点配置
     *
     * @param endpoints the endpoints configurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 获取原有默认的授权模式（授权码模式、密码模式、客户端模式、简化模式）的授权者
        List<TokenGranter> granters = new ArrayList<>(Arrays.asList(endpoints.getTokenGranter()));

        // 添加 app 用户名密码授权模式的授权者
        granters.add(new AppPasswordTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), authenticationManager));
        // 添加手机短信验证码授权模式的授权者
        granters.add(new SmsCodeTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), authenticationManager));

        CompositeTokenGranter compositeTokenGranter = new CompositeTokenGranter(granters);

        endpoints.userDetailsService(sysUserDetailsService)
                .authenticationManager(authenticationManager)
                .tokenGranter(compositeTokenGranter)
                .tokenServices(tokenServices());
    }

    /**
     * 认证服务器相关接口权限管理
     *
     * @param security a fluent configurer for security features
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * client 存储方式
     *
     * @param clients the client details configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

}
