package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.response.MerchantTransaction;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MerchantTransactionConverter implements Function<Transaction, MerchantTransaction> {
    @Override
    public MerchantTransaction apply(Transaction transaction) {
        MerchantTransaction merchantTransaction = new MerchantTransaction();
        merchantTransaction.setReferenceNo(transaction.getReferenceNo());
        merchantTransaction.setStatus(transaction.getStatus());
        return merchantTransaction;
    }
}
