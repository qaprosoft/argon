package com.qaprosoft.argon.models.db;

public class Status extends AbstractEntity {

    private StatusType status;

    public enum StatusType {
        ONLINE, OFFLINE
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }
}
