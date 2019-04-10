package com.financialhouse.reporting.repository;

import com.financialhouse.reporting.entity.Merchant;
import com.financialhouse.reporting.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(String transactionId);

    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);

    List<Transaction> findByMerchant(Merchant merchant);
}