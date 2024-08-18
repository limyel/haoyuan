package com.limyel.haoyuan.common.security.token;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenHelper implements TokenHelper {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(Object o) {
        if (o instanceof String username) {
            return jwtUtil.generateToken(username);
        }
        // todo 抛异常
        return null;
    }

    @Override
    public void validateToken(String token) throws RuntimeException {
        try {
            jwtUtil.parseToken(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new ServiceException("Token 不可用");
        } catch (ExpiredJwtException e) {
            throw new ServiceException("Token 失效");
        }
    }

    @Override
    public String getUsernameByToken(String token) {
        Claims claims = jwtUtil.parseToken(token).getBody();
        return claims.getSubject();
    }
}
