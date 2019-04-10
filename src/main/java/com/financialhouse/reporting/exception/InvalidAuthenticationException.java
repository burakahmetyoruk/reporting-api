package com.financialhouse.reporting.exception;

public class InvalidAuthenticationException extends RuntimeException {

    private final String message;

    public InvalidAuthenticationException(String message) {
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}