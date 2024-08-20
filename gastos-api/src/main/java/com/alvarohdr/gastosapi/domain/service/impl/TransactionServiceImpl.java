package com.alvarohdr.gastosapi.domain.service.impl;

import com.alvarohdr.gastosapi.domain.dao.TransactionDao;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import com.alvarohdr.gastosapi.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> listTransactions() {
        return transactionDao.findAll();
    }

    @Override
    public Optional<Transaction> findById(long transactionId) {
        return transactionDao.findById(transactionId);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionDao.delete(transaction);
    }


}
