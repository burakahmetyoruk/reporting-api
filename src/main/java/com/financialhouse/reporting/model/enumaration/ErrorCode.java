package com.financialhouse.reporting.model.enumaration;

public enum ErrorCode {
    DO_NOT_HONOR("Do not honor"),
    INVALID_TRANSACTION("Invalid TransactionsReportDto"),
    INVALID_CARD("Invalid Card"),
    NOT_SUFFICIENT_FUNDS("Not sufficient funds"),
    INCORRECT_PIN("Incorrect PIN"),
    INVALID_COUNTY_ASSOCIATION("Invalid country association"),
    CURRENCY_NOT_ALLOWED("Currency not allowed"),
    TRANSPORT_ERROR("3-D Secure Transport Error"),
    TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER("TransactionsReportDto not permitted to cardholder");

    private String text;

    private ErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
