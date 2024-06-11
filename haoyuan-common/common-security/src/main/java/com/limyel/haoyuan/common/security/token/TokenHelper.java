package com.limyel.haoyuan.common.security.token;

public interface TokenHelper {

    String generateToken(Object o);

    void validateToken(String token) throws RuntimeException;

    String getUsernameByToken(String token);

}
