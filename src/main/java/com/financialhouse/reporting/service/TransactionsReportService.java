package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.Fx;
import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;
import com.financialhouse.reporting.model.response.TransactionsReportDto;
import com.financialhouse.reporting.model.response.TransactionsReportResponse;
import com.financialhouse.reporting.repository.TransactionRepository;
import com.financialhouse.reporting.validator.TransactionsReportRequestValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsReportService {

    private final TransactionRepository transactionRepository;

    public TransactionsReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionsReportResponse retrieveTransactionsReport(TransactionsReportRequest transactionsReportRequest) {
        TransactionsReportRequestValidator.validate(transactionsReportRequest);

        final Date fromDate = Date.valueOf(transactionsReportRequest.getFromDate());
        final Date toDate = Date.valueOf(transactionsReportRequest.getToDate());
        List<Transaction> transactionList = transactionRepository.findByTransactionDateBetween(fromDate, toDate);

        transactionList = filterTransactionListWithMerchant(transactionList, transactionsReportRequest.getMerchant());
        transactionList = filterTransactionListWithAcquirer(transactionList, transactionsReportRequest.getAcquirer());

        List<TransactionsReportDto> transactionsReportDtoList = retrieveTransactionsReport(transactionList);

        final TransactionsReportResponse transactionsReportResponse = new TransactionsReportResponse();
        transactionsReportResponse.setStatus(Status.APPROVED);
        transactionsReportResponse.setTransactionsReportDtoList(transactionsReportDtoList);
        return transactionsReportResponse;
    }

    private List<Transaction> filterTransactionListWithMerchant(List<Transaction> transactionList, Integer merchantId) {
        if (merchantId != null) {
            transactionList = transactionList.stream()
                    .filter(transaction -> transaction.getMerchant().getId().intValue() == merchantId)
                    .collect(Collectors.toList());
        }
        return transactionList;
    }

    private List<Transaction> filterTransactionListWithAcquirer(List<Transaction> transactionList, Integer acquirerId) {
        if (acquirerId != null) {
            transactionList = transactionList.stream()
                    .filter(transaction -> transaction.getAcquirer().getId().intValue() == acquirerId)
                    .collect(Collectors.toList());
        }
        return transactionList;
    }

    private List<TransactionsReportDto> retrieveTransactionsReport(final List<Transaction> transactionList) {
        List<TransactionsReportDto> transactionsReportDtoList = new ArrayList<>();
        for (Currency currency : Currency.values()) {
            TransactionsReportDto transactionsReportDto = new TransactionsReportDto();
            final List<Fx> fxList = transactionList.stream()
                    .map(Transaction::getFx)
                    .filter(fx -> fx.getCurrency() == currency)
                    .collect(Collectors.toList());

            if (!fxList.isEmpty()) {
                transactionsReportDto.setCurrency(currency);
                transactionsReportDto.setCount(fxList.size());
                transactionsReportDto.setTotal(fxList.stream()
                        .map(Fx::getAmount)
                        .mapToInt(BigDecimal::intValue)
                        .sum());

                transactionsReportDtoList.add(transactionsReportDto);
            }
        }
        return transactionsReportDtoList;
    }
}