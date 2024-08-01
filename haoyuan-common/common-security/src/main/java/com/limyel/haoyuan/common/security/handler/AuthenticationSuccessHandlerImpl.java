package com.limyel.haoyuan.common.security.handler;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.util.HttpUtil;
import com.limyel.haoyuan.common.security.token.TokenHelper;
import com.limyel.haoyuan.common.security.vo.TokenVO;
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

        // todo
        HttpUtil.writeResp(response, R.ok(tokenVO));
    }
}
