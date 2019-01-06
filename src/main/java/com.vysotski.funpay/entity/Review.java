package com.vysotski.funpay.entity;

import java.sql.Date;

public class Review extends Entity {
    private long userId;
    private long serverId;
    private String reviewText;
    private Date reviewDate;

    public Review() {
    }

    public Review(long userId, long serverId, String reviewText) {
        this.userId = userId;
        this.serverId = serverId;
        this.reviewText = reviewText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
