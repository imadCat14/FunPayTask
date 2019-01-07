package com.vysotski.funpay.entity;

public class Mark extends Entity {
    private long userId;
    private long serverId;
    private int mark;

    public Mark(){}

    public Mark(long userId, long serverId, int mark) {
        this.userId = userId;
        this.serverId = serverId;
        this.mark = mark;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (serverId ^ (serverId >>> 32));
        result = prime * result + mark;
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mark other = (Mark) obj;
        if (serverId != other.serverId)
            return false;
        if (mark != other.mark)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "userId=" + userId +
                ", serverId=" + serverId +
                ", mark=" + mark +
                '}';
    }
}
