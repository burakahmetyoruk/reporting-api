package com.financialhouse.reporting.exception;

public class InvalidRequestException extends RuntimeException {

    private final String message;

    public InvalidRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}