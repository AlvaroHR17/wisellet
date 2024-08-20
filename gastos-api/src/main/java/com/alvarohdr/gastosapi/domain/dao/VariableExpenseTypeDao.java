package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;

import java.util.List;
import java.util.Optional;

public interface VariableExpenseTypeDao extends BaseDao<VariableExpenseType> {
    Optional<VariableExpenseType> findByDescription(String description);
    List<VariableExpenseType> listByUser(User user);
}
