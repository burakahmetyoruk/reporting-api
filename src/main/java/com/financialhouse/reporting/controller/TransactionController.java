package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.model.response.TransactionResponse;
import com.financialhouse.reporting.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/{transaction-id}")
    public ResponseEntity<TransactionResponse> retrieveTransaction(@PathVariable("transaction-id") String transactionId) {
        return ResponseEntity.ok(transactionService.retrieveTransaction(transactionId));
    }
}
