package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;

import java.util.List;

public interface FixedExpenseDao extends BaseDao<FixedExpense> {
    List<FixedExpense> listFixedExpensesByTypeDescription(String typeDescription);
}
