package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpenseType;
import com.alvarohdr.gastosapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseTypeDao extends BaseDao<FixedExpenseType> {
    Optional<FixedExpenseType> findByDescription(String description);
    List<FixedExpenseType> listByUser(User user);
}
