package com.limyel.haoyuan.common.security.filter;

import com.limyel.haoyuan.common.core.exception.auth.BadTokenException;
import com.limyel.haoyuan.common.core.exception.auth.TokenExpiredException;
import com.limyel.haoyuan.common.security.config.SecurityProperties;
import com.limyel.haoyuan.common.security.token.TokenHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenHelper tokenHelper;

    private final UserDetailsService userDetailsService;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(securityProperties.getToken().getHeader());
        // header 为空，放行
        if (!StringUtils.hasText(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (header.startsWith(securityProperties.getToken().getPrefix())) {
            String token = header.substring(7);
            if (!StringUtils.hasText(token)) {
                try {
                    tokenHelper.validateToken(token);
                } catch (BadTokenException e) {
                    // 触发异常，由 AuthenticationEntryPoint 统一处理
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (TokenExpiredException e) {
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    return;
                }

                String username = tokenHelper.getUsernameByToken(token);
                if (!StringUtils.hasText(username)
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    // 将用户信息存入 authenticationToken，方便后续校验
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authenticationToken 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
