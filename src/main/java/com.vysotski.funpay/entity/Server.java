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
}
