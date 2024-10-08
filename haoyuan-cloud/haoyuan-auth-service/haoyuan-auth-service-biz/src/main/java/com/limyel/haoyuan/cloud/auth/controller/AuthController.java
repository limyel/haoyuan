package com.limyel.haoyuan.cloud.auth.controller;

import com.limyel.haoyuan.cloud.auth.service.SysUserDetailsService;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.mall.common.auth.constant.GrantTypeEnum;
import com.limyel.haoyuan.mall.common.auth.dto.CheckTokenDTO;
import com.limyel.haoyuan.mall.common.auth.dto.LoginDTO;
import com.limyel.haoyuan.mall.common.auth.dto.RefreshDTO;
import com.limyel.haoyuan.mall.common.auth.entity.SysUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证鉴权")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;

    private final CheckTokenEndpoint checkTokenEndpoint;

    private final SysUserDetailsService sysUserDetailsService;

    private final StringRedisTemplate redisTemplate;

    /**
     * 自定义认证接口
     * @param dto 认证 DTO
     * @param request Servlet 请求
     * @return OAuth2 access token
     * @throws HttpRequestMethodNotSupportedException 只处理 POST 请求
     */
    @PostMapping("/login")
    @Operation(summary = "认证接口")
    @Parameter(in = ParameterIn.HEADER, name = "clientId", description = "客户端 ID", required = true)
    @Parameter(in = ParameterIn.HEADER, name = "clientSecret", description = "客户端密钥", required = true)
    public R<OAuth2AccessToken> login(@Validated @RequestBody LoginDTO dto, HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        Optional<GrantTypeEnum> grantTypeOptional = GrantTypeEnum.from(dto.getGrantType());
        GrantTypeEnum grantType = grantTypeOptional.orElseThrow(() -> new ServiceException("不支持的认证类型"));
        UsernamePasswordAuthenticationToken token = buildClientAuthentication(request);

        // 构建用户认证信息
        Map<String, String> map = new HashMap<>();
        switch (grantType) {
            case PASSWORD -> {
                map.put("username", dto.getUsername());
                map.put("password", dto.getPassword());
                map.put("grant_type", GrantTypeEnum.PASSWORD.getValue());
                break;
            }
            case APP_PASSWORD -> {
                map.put("username", dto.getUsername());
                map.put("password", dto.getPassword());
                map.put("grant_type", GrantTypeEnum.APP_PASSWORD.getValue());
                break;
            }
            default -> throw new ServiceException("不支持此认证类型");
        }

        OAuth2AccessToken result = tokenEndpoint.postAccessToken(token, map).getBody();
        return R.ok(result);
    }

    /**
     * 用 refresh token 重新签发 token
     * @param dto 刷新 token DTO
     * @param request Servlet 请求
     * @return OAuth2 access token
     * @throws HttpRequestMethodNotSupportedException 只处理 POST 请求
     */
    @PostMapping("/refresh-token")
    @Operation(summary = "刷新 token")
    public R<OAuth2AccessToken> refreshToken(@Validated @RequestBody RefreshDTO dto, HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        UsernamePasswordAuthenticationToken token = buildClientAuthentication(request);

        Map<String, String> map = new HashMap<>();
        map.put("refresh_token", dto.getRefreshToken());
        map.put("access_token", dto.getAccessToken());
        map.put("grant_type", GrantTypeEnum.REFRESH_TOKEN.getValue());

        OAuth2AccessToken result = tokenEndpoint.postAccessToken(token, map).getBody();
        return R.ok(result);
    }

    /**
     * 校验 token 的有效性，如果 redis 中没有对应的权限信息，调用该接口会重新将权限放入 redis 中
     * @param dto 校验 token DTO
     * @return token 校验结果
     */
    @PostMapping("/check-token")
    @Operation(summary = "校验 token")
    public R<Map<String, ?>> checkToken(@Validated @RequestBody CheckTokenDTO dto) {
        Map<String, ?> result = checkTokenEndpoint.checkToken(dto.getToken());
        cachePerms(result);
        return R.ok(result);
    }

    // 构建客户端认证信息
    private UsernamePasswordAuthenticationToken buildClientAuthentication(HttpServletRequest request) {
        String clientId = request.getHeader("clientId");
        String clientSecret = request.getHeader("clientSecret");
        Assert.isTrue(StringUtils.hasText(clientId) && StringUtils.hasText(clientSecret), "客户端信息不能为空。");

        User client = new User(clientId, clientSecret, Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
    }

    private void cachePerms(Map<String, ?> result) {
        Boolean active = (Boolean) result.get("active");
        if (active != null && !active) {
            return;
        }
        String username = (String) result.get("user_name");
        if (result.get("sysUserId") != null) {
            SysUserDetails userDetails = (SysUserDetails) sysUserDetailsService.loadUserByUsername(username);
            redisTemplate.opsForValue().set("AUTH:SYS_USER_PERMS:" + userDetails.getId(), JSONUtil.toJson(userDetails.getPerms()));
        }
    }

}
