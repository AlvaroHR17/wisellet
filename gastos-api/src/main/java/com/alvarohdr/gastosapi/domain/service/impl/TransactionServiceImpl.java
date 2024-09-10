package com.alvarohdr.gastosapi.domain.service.impl;

import com.alvarohdr.gastosapi.domain.dao.*;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.enums.EnumTransactionTypes;
import com.alvarohdr.gastosapi.domain.model.visitor.impl.TransactionTypeVisitor;
import com.alvarohdr.gastosapi.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final IncomeTypeDao incomeTypeDao;
    private final IncomeDao incomeDao;
    private final FixedExpenseTypeDao fixedExpenseTypeDao;
    private final FixedExpenseDao fixedExpenseDao;
    private final VariableExpenseTypeDao variableExpenseTypeDao;
    private final VariableExpenseDao variableExpenseDao;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao,
                                  IncomeTypeDao incomeTypeDao,
                                  IncomeDao incomeDao,
                                  FixedExpenseTypeDao fixedExpenseTypeDao,
                                  FixedExpenseDao fixedExpenseDao,
                                  VariableExpenseTypeDao variableExpenseTypeDao,
                                  VariableExpenseDao variableExpenseDao) {
        this.transactionDao = transactionDao;
        this.incomeTypeDao = incomeTypeDao;
        this.incomeDao = incomeDao;
        this.fixedExpenseTypeDao = fixedExpenseTypeDao;
        this.fixedExpenseDao = fixedExpenseDao;
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.variableExpenseDao = variableExpenseDao;
    }

    @Override
    public List<Transaction> listTransactionsInMonth(short month, int year) {
        return transactionDao.listInMonth(month, year);
    }

    @Override
    public Optional<Transaction> findById(long transactionId) {
        return transactionDao.findById(transactionId);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        TransactionTypeVisitor visitor = new TransactionTypeVisitor();
        EnumTransactionTypes transactionType = transaction.accept(visitor);

        transactionDao.delete(transaction);
        switch (transactionType) {
            case INCOME -> {
                Income income = (Income) transaction;
                int size = incomeDao.listIncomesByTypeDescription(income.getType().getDescription()).size();
                if(size == 0) incomeTypeDao.delete(income.getType());
            }
            case FIXED_EXPENSE -> {
                FixedExpense fixedExpense = (FixedExpense) transaction;
                int size = fixedExpenseDao.listFixedExpensesByTypeDescription(fixedExpense.getType().getDescription()).size();
                if(size == 0) fixedExpenseTypeDao.delete(fixedExpense.getType());
            }
            case VARIABLE_EXPENSE ->{
                VariableExpense variableExpense = (VariableExpense) transaction;
                int size = variableExpenseDao.listVariableExpensesByTypeDescription(variableExpense.getType().getDescription()).size();
                if(size == 0) variableExpenseTypeDao.delete(variableExpense.getType());
            }
        }
    }
}
