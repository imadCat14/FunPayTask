package com.vysotski.funpay.entity;

public enum StatusEnum {
    ACTIVE(1),BLOCKED(0);
    private int statusNum;
    StatusEnum(int statusNum){
        this.statusNum = statusNum;
    }
    public static StatusEnum takeStatus(int statusNum){
        StatusEnum status = null;
        switch(statusNum){
            case(1): status = ACTIVE;
            break;
            case(0): status = BLOCKED;
            break;
        }
        return status;
    }
}
