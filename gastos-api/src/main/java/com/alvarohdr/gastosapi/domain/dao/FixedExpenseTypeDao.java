package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpenseType;

import java.util.Optional;

public interface FixedExpenseTypeDao extends BaseDao<FixedExpenseType> {
    Optional<FixedExpenseType> findByDescription(String description);
}
