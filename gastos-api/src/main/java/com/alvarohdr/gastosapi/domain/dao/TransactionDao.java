package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.Transaction;

import java.util.Optional;

public interface TransactionDao extends BaseDao<Transaction> {
    Optional<Transaction> findById(long id);
}
