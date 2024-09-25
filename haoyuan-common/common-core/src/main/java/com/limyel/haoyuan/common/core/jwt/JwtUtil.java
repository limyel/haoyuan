package com.limyel.haoyuan.common.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    public static final String JWT_PAYLOAD = "user";
    public static final String JWT_HEADER = "authorization";
    /** 分割jwt与刷新令牌的标识 */
    public static final String JWT_SLIPTER = "abcdefg.uvwxyz";

    public static final char ENCODE_CHAR = 'a';

    /**
     * 生成 jwt 令牌
     * @param payload
     * @param privateKey
     * @param expire
     * @return
     */
    public static String generateJwtToken(String payload, PrivateKey privateKey, int expire) {
        return Jwts.builder().claim(JWT_PAYLOAD, payload).setId(createJtl())
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000L))
                .signWith(privateKey, SignatureAlgorithm.RS256).compact();
    }

    /**
     * 生成刷新令牌
     * @param payload
     * @param privateKey
     * @param expire
     * @return
     */
    public static String generateRefreshToken(String payload, PrivateKey privateKey, int expire) {
        String encodedTokenJson = encodeRefreshToken(payload);
        return Jwts.builder().claim(JWT_PAYLOAD, encodedTokenJson).setId(createJtl())
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000L))
                .signWith(privateKey, SignatureAlgorithm.RS256).compact();
    }

    /**
     * 解析 JWT 令牌
     * @param token
     * @param publicKey
     * @return
     */
    public static TokenPayload parseToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        TokenPayload payload = new TokenPayload();
        payload.setId(claims.getId());
        payload.setExpiration(claims.getExpiration());
        payload.setPayload(claims.get(JWT_PAYLOAD).toString());
        return payload;
    }

    public static String createJtl() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    public static String encodeRefreshToken(String payload) {
        StringBuilder sb = new StringBuilder(payload.length() + 10);
        sb.append("refresh ");
        char[] chars = payload.toCharArray();
        for (char c : chars) {
            c ^= ENCODE_CHAR;
            sb.append(c);
        }
        String encodedTokenJson = sb.toString();
        return new String(Base64Utils.encode(encodedTokenJson.getBytes()));
    }

}
