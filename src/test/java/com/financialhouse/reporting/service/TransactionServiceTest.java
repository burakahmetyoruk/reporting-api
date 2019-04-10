package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.*;
import com.financialhouse.reporting.exception.TransactionNotFoundException;
import com.financialhouse.reporting.model.converter.*;
import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.response.*;
import com.financialhouse.reporting.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CustomerInfoConverter customerInfoConverter;
    @Mock
    private FxConverter fxConverter;
    @Mock
    private AcquirerConverter acquirerConverter;
    @Mock
    private MerchantConverter merchantConverter;
    @Mock
    private MerchantTransactionConverter merchantTransactionConverter;

    @Before
    public void setUp() {
        transactionService = new TransactionService(transactionRepository,
                customerInfoConverter,
                fxConverter,
                acquirerConverter,
                merchantConverter,
                merchantTransactionConverter);
    }

    @Test
    public void should_retrieve_transaction_with_id() {
        String transactionId = "transaction-id";

        Merchant merchant = new Merchant();
        merchant.setId(1L);

        CustomerInfo customerInfo = retrieveCustomerInfo();
        Acquirer acquirer = retrieveAcquirer();
        Fx fx = retrieveFx();

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setMerchant(merchant);
        transaction.setStatus(Status.APPROVED);
        transaction.setReferenceNo("reference-no");
        transaction.setCustomerInfo(customerInfo);
        transaction.setAcquirer(acquirer);
        transaction.setFx(fx);

        Transaction merchantTransaction = new Transaction();
        merchantTransaction.setId(2L);
        merchantTransaction.setMerchant(merchant);
        merchantTransaction.setStatus(Status.DECLINED);
        merchantTransaction.setReferenceNo("reference-no-2");

        when(transactionRepository.findByTransactionId(transactionId)).thenReturn(Optional.of(transaction));
        when(transactionRepository.findByMerchant(merchant)).thenReturn(Arrays.asList(transaction, merchantTransaction));
        when(customerInfoConverter.apply(transaction.getCustomerInfo())).thenReturn(retrieveCustomerInfoDto());
        when(fxConverter.apply(transaction.getFx())).thenReturn(retrieveFxDto());
        when(acquirerConverter.apply(transaction.getAcquirer())).thenReturn(retrieveAcquirerDto());
        when(merchantConverter.apply(merchant)).thenReturn(retrieveMerchantDto());

        final TransactionResponse transactionResponse = transactionService.retrieveTransaction(transactionId);

        assertNotNull(transactionResponse);

        assertEquals(transaction.getReferenceNo(), transactionResponse.getReferenceNo());
        assertEquals(retrieveAcquirerDto(), transactionResponse.getAcquirerDto());
        assertEquals(retrieveFxDto(), transactionResponse.getFxDto());
    }

    @Test(expected = TransactionNotFoundException.class)
    public void should_throw_transaction_not_found_exception_when_transaction_is_not_present() {

        String transactionId = "transaction-id";
        when(transactionRepository.findByTransactionId(transactionId)).thenReturn(Optional.empty());

        transactionService.retrieveTransaction(transactionId);
    }

    private CustomerInfo retrieveCustomerInfo() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setBillingFirstName("first-name");
        customerInfo.setBillingLastName("last-name");
        customerInfo.setEmail("email@email.com");
        customerInfo.setCreatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setUpdatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setNumber(UUID.randomUUID().toString());
        return customerInfo;
    }

    private CustomerInfoDto retrieveCustomerInfoDto() {
        CustomerInfoDto customerInfo = new CustomerInfoDto();
        customerInfo.setBillingFirstName("first-name");
        customerInfo.setBillingLastName("last-name");
        customerInfo.setEmail("email@email.com");
        customerInfo.setCreatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setUpdatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setNumber(UUID.randomUUID().toString());
        return customerInfo;
    }

    private Acquirer retrieveAcquirer() {
        Acquirer acquirer = new Acquirer();
        acquirer.setId(1L);
        acquirer.setName("name");
        return acquirer;
    }

    private AcquirerDto retrieveAcquirerDto() {
        AcquirerDto acquirer = new AcquirerDto();
        acquirer.setCode(1L);
        acquirer.setName("name");
        return acquirer;
    }

    private Fx retrieveFx() {
        Fx fx = new Fx();
        fx.setCurrency(Currency.EUR);
        fx.setAmount(BigDecimal.TEN);
        return fx;
    }

    private FxDto retrieveFxDto() {
        FxDto fx = new FxDto();
        fx.setOriginalCurrency(Currency.EUR);
        fx.setOriginalAmount(BigDecimal.TEN);
        return fx;
    }

    private MerchantDto retrieveMerchantDto() {
        MerchantDto merchant = new MerchantDto();
        merchant.setId(1L);
        return merchant;
    }
}