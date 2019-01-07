package com.vysotski.funpay.entity;

import java.sql.Date;

public class Review extends Entity {
    private long userId;
    private long serverId;
    private String reviewText;
    private String login;
    private String serverName;
    private Date reviewDate;

    public Review() {
    }

    public Review(long userId, long serverId, String reviewText) {
        this.userId = userId;
        this.serverId = serverId;
        this.reviewText = reviewText;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (serverId != other.serverId)
            return false;
        if (serverName == null) {
            if (other.serverName != null)
                return false;
        } else if (!serverName.equals(other.serverName))
            return false;
        if (reviewDate == null) {
            if (other.reviewDate != null)
                return false;
        } else if (!reviewDate.equals(other.reviewDate))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (reviewText == null) {
            if (other.reviewText != null)
                return false;
        } else if (!reviewText.equals(other.reviewText))
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId=" + userId +
                ", serverId=" + serverId +
                ", reviewText='" + reviewText + '\'' +
                ", login='" + login + '\'' +
                ", serverName='" + serverName + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
