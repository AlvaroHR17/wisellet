package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;

import java.util.List;

public interface VariableExpenseDao extends BaseDao<VariableExpense> {
    List<VariableExpense> listVariableExpensesByTypeDescription(String typeDescription);
}
