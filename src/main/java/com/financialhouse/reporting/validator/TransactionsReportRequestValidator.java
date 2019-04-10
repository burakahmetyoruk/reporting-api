package com.financialhouse.reporting.validator;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionsReportRequestValidator {

    public static void validate(final TransactionsReportRequest transactionsReportRequest) {
        validateDate(transactionsReportRequest.getFromDate());
        validateDate(transactionsReportRequest.getToDate());
    }

    private static void validateDate(String date) {
        try {
            final String pattern = "yyyy-MM-dd";
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidRequestException("date format is invalid. The correct format is yyyy-MM-dd");
        }
    }
}
