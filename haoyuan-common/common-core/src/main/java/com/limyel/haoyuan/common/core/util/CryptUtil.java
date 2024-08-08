package com.limyel.haoyuan.common.core.util;

import org.mindrot.jbcrypt.BCrypt;

public abstract class CryptUtil {

    public static String encrypt(String pwd) {
        return BCrypt.hashpw(pwd, BCrypt.gensalt());
    }

    public static boolean match(String pwd, String encodedPwd) {
        return BCrypt.checkpw(pwd, encodedPwd);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123456"));;
    }

}
