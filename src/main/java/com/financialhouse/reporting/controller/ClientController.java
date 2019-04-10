package com.financialhouse.reporting.controller;

import com.financialhouse.reporting.model.response.CustomerInfoDto;
import com.financialhouse.reporting.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/{transaction-id}")
    public ResponseEntity<CustomerInfoDto> retrieveCustomerInfo(@PathVariable("transaction-id") String transactionId) {
        return ResponseEntity.ok(clientService.retrieveCustomerInfo(transactionId));
    }
}