package com.vysotski.funpay.validator;

public class ServerValidator {
    private static final String SERVER_NAME_PATTERN = "[\\w\\s]{1,20}";
    private static final String SERVER_CHRONICLE_PATTERN ="[\\w\\s]{1,25}";
    private static final String SERVER_DESCRIPTION_PATTERN="[\\w\\s\\p{Punct}]{2,1000}";

    public static boolean validateServerData(String name, String chronicle, String description){
        return (name.matches(SERVER_NAME_PATTERN)&&chronicle.matches(SERVER_CHRONICLE_PATTERN)
                &&description.matches(SERVER_DESCRIPTION_PATTERN));
    }
    public static boolean validateServerDescription(String description){
        return (description.matches(SERVER_DESCRIPTION_PATTERN));
    }
}
