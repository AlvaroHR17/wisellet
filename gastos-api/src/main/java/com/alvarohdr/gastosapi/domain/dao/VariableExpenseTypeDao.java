package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;

import java.util.Optional;

public interface VariableExpenseTypeDao extends BaseDao<VariableExpenseType> {
    Optional<VariableExpenseType> findByDescription(String description, long userId);
}
