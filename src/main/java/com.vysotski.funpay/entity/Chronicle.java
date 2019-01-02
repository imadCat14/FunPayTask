package com.vysotski.funpay.entity;

public class Chronicle extends Entity {
    private long chronicleId;
    private String chronicleName;

    public Chronicle(){}

    public Chronicle(String chronicleName){
        this.chronicleName = chronicleName;
    }

    public Chronicle(long chronicleId, String chronicleName) {
        this.chronicleId = chronicleId;
        this.chronicleName = chronicleName;
    }

    public long getChronicleId() {
        return chronicleId;
    }

    public void setChronicleId(long chronicleId) {
        this.chronicleId = chronicleId;
    }

    public String getChronicleName() {
        return chronicleName;
    }

    public void setChronicleName(String chronicleName) {
        this.chronicleName = chronicleName;
    }
}
