package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.response.MerchantTransaction;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MerchantTransactionConverterTest {

    private MerchantTransactionConverter merchantTransactionConverter;

    @Before
    public void setUp() {
        merchantTransactionConverter = new MerchantTransactionConverter();
    }

    @Test
    public void should_convert_transaction_to_merchant_transaction() {
        Transaction transaction = new Transaction();
        transaction.setStatus(Status.DECLINED);
        transaction.setReferenceNo(UUID.randomUUID().toString());

        final MerchantTransaction merchantTransaction = merchantTransactionConverter.apply(transaction);

        assertNotNull(merchantTransaction);
        assertEquals(transaction.getReferenceNo(), merchantTransaction.getReferenceNo());
        assertEquals(transaction.getStatus(), merchantTransaction.getStatus());
    }
}