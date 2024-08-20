package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;

import java.util.List;
import java.util.Optional;

public interface VariableExpenseDao extends BaseDao<VariableExpense> {
    Optional<VariableExpense> findById(long id);
    List<VariableExpense> listVariableExpensesByTypeDescription(String typeDescription);
}
