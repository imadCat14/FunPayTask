package com.vysotski.funpay.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String LOGIN_PATTERN="^[a-zA-Z][a-zA-Z0-9-_]{4,20}$";


    public static boolean isLoginValid (String login){
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }
}
