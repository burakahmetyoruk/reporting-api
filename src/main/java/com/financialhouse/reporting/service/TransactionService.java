package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.Merchant;
import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.exception.TransactionNotFoundException;
import com.financialhouse.reporting.model.converter.*;
import com.financialhouse.reporting.model.response.*;
import com.financialhouse.reporting.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerInfoConverter customerInfoConverter;
    private final FxConverter fxConverter;
    private final AcquirerConverter acquirerConverter;
    private final MerchantConverter merchantConverter;
    private final MerchantTransactionConverter merchantTransactionConverter;

    public TransactionService(TransactionRepository transactionRepository,
                              CustomerInfoConverter customerInfoConverter,
                              FxConverter fxConverter,
                              AcquirerConverter acquirerConverter,
                              MerchantConverter merchantConverter,
                              MerchantTransactionConverter merchantTransactionConverter) {
        this.transactionRepository = transactionRepository;
        this.customerInfoConverter = customerInfoConverter;
        this.fxConverter = fxConverter;
        this.acquirerConverter = acquirerConverter;
        this.merchantConverter = merchantConverter;
        this.merchantTransactionConverter = merchantTransactionConverter;
    }

    public TransactionResponse retrieveTransaction(String transactionId) {
        final Optional<Transaction> transactionOptional = transactionRepository.findByTransactionId(transactionId);
        transactionOptional.orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + transactionId));

        Transaction transaction = transactionOptional.get();
        final Merchant merchant = transaction.getMerchant();
        final List<Transaction> merchantTransactionList = transactionRepository.findByMerchant(merchant);
        final List<MerchantTransaction> merchantTransactions = merchantTransactionList.stream()
                .map(merchantTransactionConverter)
                .collect(Collectors.toList());

        final CustomerInfoDto customerInfoDto = customerInfoConverter.apply(transaction.getCustomerInfo());
        final FxDto fxDto = fxConverter.apply(transaction.getFx());
        final AcquirerDto acquirerDto = acquirerConverter.apply(transaction.getAcquirer());
        final MerchantDto merchantDto = merchantConverter.apply(merchant);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setCustomerInfoDto(customerInfoDto);
        transactionResponse.setFxDto(fxDto);
        transactionResponse.setAcquirerDto(acquirerDto);
        transactionResponse.setMerchantDto(merchantDto);
        transactionResponse.setReferenceNo(transaction.getReferenceNo());
        transactionResponse.setMerchantTransactionList(merchantTransactions);
        return transactionResponse;
    }
}