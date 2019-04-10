package com.financialhouse.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financialhouse.reporting.model.enumaration.Status;

import java.util.Collections;
import java.util.List;

public class TransactionsReportResponse {

    private Status status;

    @JsonProperty(value = "response")
    private List<TransactionsReportDto> transactionsReportDtoList = Collections.emptyList();

    public List<TransactionsReportDto> getTransactionsReportDtoList() {
        return transactionsReportDtoList;
    }

    public void setTransactionsReportDtoList(List<TransactionsReportDto> transactionsReportDtoList) {
        this.transactionsReportDtoList = transactionsReportDtoList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
