package com.vysotski.funpay.entity;

public class Server extends Entity {
    private long serverId;
    private String serverName;
    private String description;
    private Chronicle chronicle;
    private double averageMark;

    public Server() {
    }

    public Server(long serverID, String serverName, String description, Chronicle chronicle) {
        this.serverId = serverID;
        this.serverName = serverName;
        this.description = description;
        this.chronicle = chronicle;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chronicle getChronicle() {
        return chronicle;
    }

    public void setChronicle(Chronicle chronicle) {
        this.chronicle = chronicle;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (serverId ^ (serverId >>> 32));
        result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
        long temp;
        temp = Double.doubleToLongBits(averageMark);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((chronicle == null) ? 0 : chronicle.hashCode());
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
        Server other = (Server) obj;
        if (serverId != other.serverId)
            return false;
        if (serverName == null) {
            if (other.serverName != null)
                return false;
        } else if (!serverName.equals(other.serverName))
            return false;
        if (Double.doubleToLongBits(averageMark) != Double.doubleToLongBits(other.averageMark))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (chronicle == null) {
            if (other.chronicle != null)
                return false;
        } else if (!chronicle.equals(other.chronicle))
            return false;
        return true;
    }
}
