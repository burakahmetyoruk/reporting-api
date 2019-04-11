package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.Acquirer;
import com.financialhouse.reporting.entity.Fx;
import com.financialhouse.reporting.entity.Merchant;
import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;
import com.financialhouse.reporting.model.response.TransactionsReportDto;
import com.financialhouse.reporting.model.response.TransactionsReportResponse;
import com.financialhouse.reporting.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsReportDtoServiceTest {

    private static final String FROM_DATE = "2019-03-01";
    private static final String TO_DATE = "2019-03-04";

    private TransactionsReportService transactionsReportService;

    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionsReportService = new TransactionsReportService(transactionRepository);
    }

    @Test
    public void should_retrieve_transactions_report_between_date() {
        //given
        Date fromDate = Date.valueOf(FROM_DATE);
        Date toDate = Date.valueOf(TO_DATE);

        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setFromDate(FROM_DATE);
        transactionsReportRequest.setToDate(TO_DATE);

        when(transactionRepository.findByTransactionDateBetween(fromDate, toDate)).thenReturn(retrieveTransactionList());

        //when
        final TransactionsReportResponse transactionsReportResponse = transactionsReportService.retrieveTransactionsReport(transactionsReportRequest);

        //then
        assertNotNull(transactionsReportResponse);
        final List<TransactionsReportDto> transactionsReportDtoList = transactionsReportResponse.getTransactionsReportDtoList();
        assertNotNull(transactionsReportDtoList);
        assertEquals(3, transactionsReportDtoList.size());

        TransactionsReportDto expectedTransactionReport = transactionsReportDtoList.stream()
                .filter(transactionsReportDto -> transactionsReportDto.getCurrency() == Currency.USD)
                .findFirst()
                .get();
        assertEquals(20, expectedTransactionReport.getTotal().intValue());
        assertEquals(2, expectedTransactionReport.getCount().intValue());
    }

    @Test
    public void should_retrieve_transactions_report_between_date_and_filter_with_merchant_id() {
        //given
        Date fromDate = Date.valueOf(FROM_DATE);
        Date toDate = Date.valueOf(TO_DATE);

        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setFromDate(FROM_DATE);
        transactionsReportRequest.setToDate(TO_DATE);
        transactionsReportRequest.setMerchant(1);

        when(transactionRepository.findByTransactionDateBetween(fromDate, toDate)).thenReturn(retrieveTransactionList());

        //when
        final TransactionsReportResponse transactionsReportResponse = transactionsReportService.retrieveTransactionsReport(transactionsReportRequest);

        //then
        assertNotNull(transactionsReportResponse);
        final List<TransactionsReportDto> transactionsReportDtoList = transactionsReportResponse.getTransactionsReportDtoList();
        assertNotNull(transactionsReportDtoList);
        assertEquals(1, transactionsReportDtoList.size());
    }

    @Test
    public void should_retrieve_transactions_report_between_date_and_filter_with_acquirer_id() {
        //given
        Date fromDate = Date.valueOf(FROM_DATE);
        Date toDate = Date.valueOf(TO_DATE);

        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setFromDate(FROM_DATE);
        transactionsReportRequest.setToDate(TO_DATE);
        transactionsReportRequest.setAcquirer(1);

        when(transactionRepository.findByTransactionDateBetween(fromDate, toDate)).thenReturn(retrieveTransactionList());

        //when
        final TransactionsReportResponse transactionsReportResponse = transactionsReportService.retrieveTransactionsReport(transactionsReportRequest);

        //then
        assertNotNull(transactionsReportResponse);
        final List<TransactionsReportDto> transactionsReportDtoList = transactionsReportResponse.getTransactionsReportDtoList();
        assertNotNull(transactionsReportDtoList);
        assertEquals(1, transactionsReportDtoList.size());
    }

    private List<Transaction> retrieveTransactionList() {
        List<Transaction> transactionList = new ArrayList<>();

        Merchant merchant = new Merchant();
        merchant.setId(1L);

        Merchant merchant2 = new Merchant();
        merchant2.setId(2L);

        Acquirer acquirer = new Acquirer();
        acquirer.setId(1L);

        Acquirer acquirer2 = new Acquirer();
        acquirer2.setId(2L);

        Fx fxEur = new Fx();
        fxEur.setAmount(BigDecimal.ONE);
        fxEur.setCurrency(Currency.EUR);
        fxEur.setId(1L);

        Fx fxUsd = new Fx();
        fxUsd.setAmount(BigDecimal.TEN);
        fxUsd.setCurrency(Currency.USD);
        fxUsd.setId(2L);

        Fx fxEur2 = new Fx();
        fxEur2.setAmount(BigDecimal.ONE);
        fxEur2.setCurrency(Currency.EUR);
        fxEur2.setId(3L);

        Fx fxUsd2 = new Fx();
        fxUsd2.setAmount(BigDecimal.TEN);
        fxUsd2.setCurrency(Currency.USD);
        fxUsd2.setId(4L);

        Fx fxGbp = new Fx();
        fxGbp.setAmount(BigDecimal.ONE);
        fxGbp.setCurrency(Currency.GBP);
        fxGbp.setId(5L);

        Transaction transaction = new Transaction();
        transaction.setFx(fxEur);
        transaction.setTransactionDate(Date.valueOf("2019-03-02"));
        transaction.setMerchant(merchant);
        transaction.setAcquirer(acquirer2);

        Transaction transaction2 = new Transaction();
        transaction2.setFx(fxEur2);
        transaction2.setTransactionDate(Date.valueOf("2019-03-02"));
        transaction2.setMerchant(merchant2);
        transaction2.setAcquirer(acquirer2);

        Transaction transaction3 = new Transaction();
        transaction3.setFx(fxUsd);
        transaction3.setTransactionDate(Date.valueOf("2019-03-03"));
        transaction3.setMerchant(merchant2);
        transaction3.setAcquirer(acquirer2);

        Transaction transaction4 = new Transaction();
        transaction4.setFx(fxUsd2);
        transaction4.setTransactionDate(Date.valueOf("2019-03-03"));
        transaction4.setMerchant(merchant2);
        transaction4.setAcquirer(acquirer2);

        Transaction transaction5 = new Transaction();
        transaction5.setFx(fxGbp);
        transaction5.setTransactionDate(Date.valueOf("2019-03-03"));
        transaction5.setMerchant(merchant2);
        transaction5.setAcquirer(acquirer);

        transactionList.add(transaction);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        transactionList.add(transaction5);

        return transactionList;
    }
}