package com.limyel.haoyuan.mall.security.extention.sms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SmsCodeAuthentication extends UsernamePasswordAuthenticationToken {

    public SmsCodeAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

}
