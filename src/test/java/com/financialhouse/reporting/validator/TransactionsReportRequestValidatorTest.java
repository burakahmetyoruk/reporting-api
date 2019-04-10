package com.financialhouse.reporting.validator;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;
import org.junit.Test;

public class TransactionsReportRequestValidatorTest {

    @Test
    public void should_validate() {
        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setToDate("2019-04-09");
        transactionsReportRequest.setFromDate("2019-04-07");

        TransactionsReportRequestValidator.validate(transactionsReportRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_throw_invalid_request_exception() {
        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setToDate("2019-04-09");
        transactionsReportRequest.setFromDate("2019_04_07");

        TransactionsReportRequestValidator.validate(transactionsReportRequest);
    }
}