package com.vysotski.funpay.util;
import org.apache.commons.codec.binary.Base64;

public class PasswordEncoder {
    public static String encodePassword(String password){
        Base64 base64=new Base64();
        String encodedPassword= new String(base64.encode(password.getBytes()));
        return encodedPassword;
    }
}
