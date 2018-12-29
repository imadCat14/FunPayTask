package com.vysotski.funpay.validator;

public class ServerValidator {
    private static final String SERVER_NAME_PATTERN = "[\\w\\s]{1,20}";
    private static final String SERVER_CHRONICLE_PATTERN ="[\\w\\s]{1,25}";
    private static final String SERVER_DESCRIPTION_PATTERN ="\\w{1,100}";

    public static boolean validateAlienData(String name, String chronicle, String description){
        return (name.matches(SERVER_NAME_PATTERN)&&chronicle.matches(SERVER_CHRONICLE_PATTERN)
                &&description.matches(SERVER_DESCRIPTION_PATTERN));
    }
}
