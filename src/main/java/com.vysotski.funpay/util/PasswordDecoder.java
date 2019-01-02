package com.vysotski.funpay.util;

import org.apache.commons.codec.binary.Base64;

public class PasswordDecoder {
    public static String decodePassword(String password){
        Base64 base64=new Base64();
        String decodedPassword= new String(base64.decode(password.getBytes()));
        return decodedPassword;
    }
}
