package com.limyel.haoyuan.admin.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.limyel.haoyuan.common.core.util.JsonUtil;
import com.limyel.haoyuan.common.jwt.config.JwtProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@Component
//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // todo 放入 security 配置文件中

    /**
     * 指定登录地址
     */
    public JwtAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 用户认证逻辑
     * @param request from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OpenID).
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 解析提交的数据
        JsonNode jsonNode = JsonUtil.OBJECT_MAPPER.readTree(request.getInputStream());
        JsonNode usernameNode = jsonNode.get("username");
        JsonNode passwordNode = jsonNode.get("password");

        if (usernameNode == null || passwordNode == null
            || !StringUtils.hasText(usernameNode.textValue()) || !StringUtils.hasText(passwordNode.textValue())) {
            throw new UsernameOrPasswordNullException("用户名或密码为空");
        }

        String username = usernameNode.textValue();
        String password = passwordNode.textValue();

        // 封装用户名、密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
