package com.financialhouse.reporting.validator;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionsReportRequestValidator {

    public static void validate(final TransactionsReportRequest transactionsReportRequest) {
        final String pattern = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            simpleDateFormat.parse(transactionsReportRequest.getFromDate());
            simpleDateFormat.parse(transactionsReportRequest.getToDate());
        } catch (ParseException e) {
            throw new InvalidRequestException("date formats are invalid. The correct format is yyyy-MM-dd");
        }
    }
}
