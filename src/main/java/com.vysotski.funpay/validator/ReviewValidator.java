package com.vysotski.funpay.validator;

public class ReviewValidator {
    private static final String SERVER_REVIEW_PATTERN ="[\\w\\s\\p{Punct}]{2,300}";

    public static boolean validateReviewData(String textReview){
        return (textReview.matches(SERVER_REVIEW_PATTERN));
    }
}
