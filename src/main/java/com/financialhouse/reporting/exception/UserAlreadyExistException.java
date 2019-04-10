package com.financialhouse.reporting.exception;

public class UserAlreadyExistException extends RuntimeException {

    private final String message;

    public UserAlreadyExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}