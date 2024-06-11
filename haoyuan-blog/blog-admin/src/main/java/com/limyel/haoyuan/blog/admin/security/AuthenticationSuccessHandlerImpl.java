package com.limyel.haoyuan.blog.admin.security;

import com.limyel.haoyuan.common.jwt.util.JwtTokenHelper;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.util.RespUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);

        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);

        RespUtil.writeResp(response, R.ok(tokenVO));
    }
}
