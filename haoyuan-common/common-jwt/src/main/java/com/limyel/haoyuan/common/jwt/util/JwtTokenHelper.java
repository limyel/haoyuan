package com.limyel.haoyuan.common.jwt.util;

import com.limyel.haoyuan.common.jwt.config.JwtProperties;
import com.limyel.haoyuan.common.core.exception.auth.BadTokenException;
import com.limyel.haoyuan.common.core.exception.auth.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenHelper implements InitializingBean {

    private final JwtProperties properties;

    /**
     * 密钥
     */
    private Key key;

    /**
     * JWT 解析
     */
    private JwtParser jwtParser;

    @Override
    public void afterPropertiesSet() throws Exception {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(properties.getSecret()));

        jwtParser = Jwts.parserBuilder().requireIssuer(properties.getIssuer())
                .setSigningKey(key)
                // 能容忍的最大时钟误差
                .setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 生成 jwt token
     * @param username
     * @return
     */
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // token 一小时后失效
        LocalDateTime expireTime = now.plusHours(1);

        return Jwts.builder().setSubject(username)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    /**
     * 解析 jwt token
     * @param token
     * @return
     */
    public Jws<Claims> parseToken(String token) {
        try {
            return jwtParser.parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new BadTokenException("Token 不可用", e);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token 失效", e);
        }
    }

    /**
     * 校验 Token 是否可用
     * @param token
     */
    public void validateToken(String token) {
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析 Token 获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成 Base64 的安全秘钥
     * @return
     */
    private static String generateBase64Key() {
        // 生成安全密钥
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 将密钥进行 Base64 编码
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static void main(String[] args) {
        System.out.println("key: " + generateBase64Key());
    }
}
