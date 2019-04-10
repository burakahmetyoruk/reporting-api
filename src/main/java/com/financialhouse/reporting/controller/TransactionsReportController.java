package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.model.request.TransactionsReportRequest;
import com.financialhouse.reporting.model.response.TransactionsReportResponse;
import com.financialhouse.reporting.service.TransactionsReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/transactions")
public class TransactionsReportController {

    private final TransactionsReportService transactionsReportService;

    public TransactionsReportController(TransactionsReportService transactionsReportService) {
        this.transactionsReportService = transactionsReportService;
    }

    @PostMapping("/report")
    public ResponseEntity<TransactionsReportResponse> retrieveTransactionsReport(@RequestBody TransactionsReportRequest transactionsReportRequest) {
        return ResponseEntity.ok(transactionsReportService.retrieveTransactionsReport(transactionsReportRequest));
    }
}