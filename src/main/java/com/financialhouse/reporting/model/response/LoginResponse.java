package com.financialhouse.reporting.model.response;

import com.financialhouse.reporting.model.enumaration.Status;

public class LoginResponse {

    private String token;
    private Status status;

    public LoginResponse() {
    }

    public LoginResponse(String token, Status status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}