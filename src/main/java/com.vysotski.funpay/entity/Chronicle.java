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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (chronicleId ^ (chronicleId >>> 32));
        result = prime * result + ((chronicleName == null) ? 0 : chronicleName.hashCode());
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
        Chronicle other = (Chronicle) obj;
        if (chronicleId != other.chronicleId)
            return false;
        if (chronicleName == null) {
            if (other.chronicleName != null)
                return false;
        } else if (!chronicleName.equals(other.chronicleName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Chronicle{" +
                "chronicleId=" + chronicleId +
                ", chronicleName='" + chronicleName + '\'' +
                '}';
    }
}
