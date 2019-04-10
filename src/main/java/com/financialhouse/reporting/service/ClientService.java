package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.converter.CustomerInfoConverter;
import com.financialhouse.reporting.model.response.CustomerInfoDto;
import com.financialhouse.reporting.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final CustomerInfoConverter customerInfoConverter;
    private final TransactionRepository transactionRepository;

    public ClientService(CustomerInfoConverter customerInfoConverter,
                         TransactionRepository transactionRepository) {
        this.customerInfoConverter = customerInfoConverter;
        this.transactionRepository = transactionRepository;
    }

    public CustomerInfoDto retrieveCustomerInfo(String transactionId) {
        final Optional<Transaction> transactionOptional = transactionRepository.findByTransactionId(transactionId);

        return transactionOptional
                .map(Transaction::getCustomerInfo)
                .map(customerInfoConverter)
                .orElseGet(CustomerInfoDto::new);
    }
}