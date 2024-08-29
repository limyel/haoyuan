package com.limyel.haoyuan.mall.security.filter;

import com.limyel.haoyuan.common.core.util.JwtUtil;
import com.limyel.haoyuan.mall.security.entity.LoginUser;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final SysUserApi sysUserApi;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        // 没有 token，放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        Jws<Claims> jws = jwtUtil.parseToken(token);
        Claims claims = jws.getBody();
        String username = claims.getSubject();
        SysUserSecurity sysUser = sysUserApi.getByUsername(username);
        LoginUser loginUser = new LoginUser(sysUser, Arrays.asList("admin"));
        // 存入 SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }

}
