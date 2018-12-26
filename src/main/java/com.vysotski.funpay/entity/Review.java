package com.vysotski.funpay.entity;

import java.sql.Date;

public class Review extends Entity {
    private long userId;
    private long serverId;
    private String textReview;
    private Date dateReview;

    public Review() {
    }

    public Review(long userId, long serverId, String textReview) {
        this.userId = userId;
        this.serverId = serverId;
        this.textReview = textReview;
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

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }
}
