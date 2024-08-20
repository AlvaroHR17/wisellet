package com.alvarohdr.gastosapi.domain.service;

import com.alvarohdr.gastosapi.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> listTransactions();

    Optional<Transaction> findById(long transactionId);
    void deleteTransaction(Transaction transaction);
}
