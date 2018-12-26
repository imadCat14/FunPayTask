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
}
