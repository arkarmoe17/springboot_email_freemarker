package com.arkarmoe.email_notification.model.response;

/**
 * Created by Arkar
 * 25-July-2019
 * **/
public class EmailResponse {
    private String status;
    private String message;

    public EmailResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
