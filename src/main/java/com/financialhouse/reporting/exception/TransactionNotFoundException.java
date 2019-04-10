package com.financialhouse.reporting.exception;

public class TransactionNotFoundException extends RuntimeException {

    private final String message;

    public TransactionNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
