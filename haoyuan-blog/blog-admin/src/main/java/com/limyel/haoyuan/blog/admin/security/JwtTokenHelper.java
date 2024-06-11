package com.limyel.haoyuan.blog.admin.security;

import com.limyel.haoyuan.common.core.exception.auth.BadTokenException;
import com.limyel.haoyuan.common.core.exception.auth.TokenExpiredException;
import com.limyel.haoyuan.common.jwt.util.JwtUtil;
import com.limyel.haoyuan.common.security.token.TokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
            throw new BadTokenException("Token 不可用", e);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token 失效", e);
        }
    }

    @Override
    public String getUsernameByToken(String token) {
        Claims claims = jwtUtil.parseToken(token).getBody();
        return claims.getSubject();
    }
}
