package com.limyel.haoyuan.common.core.util;

import com.limyel.haoyuan.common.core.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
public class JwtUtil implements InitializingBean {

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
        LocalDateTime expireTime = now.plusHours(properties.getExpireHours());

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
        return jwtParser.parseClaimsJws(token);
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
