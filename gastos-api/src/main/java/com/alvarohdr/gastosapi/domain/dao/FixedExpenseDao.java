package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseDao extends BaseDao<FixedExpense> {
    Optional<FixedExpense> findById(long id);
    List<FixedExpense> listFixedExpensesByTypeDescription(String typeDescription);
}
