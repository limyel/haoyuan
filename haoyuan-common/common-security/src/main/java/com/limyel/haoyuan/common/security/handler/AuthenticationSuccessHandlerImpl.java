package com.limyel.haoyuan.common.security.handler;

import com.limyel.haoyuan.common.security.token.TokenHelper;
import com.limyel.haoyuan.common.security.vo.TokenVO;
import com.limyel.haoyuan.common.web.pojo.R;
import com.limyel.haoyuan.common.web.util.RespUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final TokenHelper tokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名生成 token
        String username = userDetails.getUsername();
        String token = tokenHelper.generateToken(username);

        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);

        RespUtil.writeResp(response, R.ok(tokenVO));
    }
}
