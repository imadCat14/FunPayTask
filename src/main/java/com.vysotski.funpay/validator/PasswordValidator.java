package com.vysotski.funpay.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String PASSWORD_PATTERN="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}$";


    public static boolean isPasswordValid (String value){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
