package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IncomeTypeDao extends BaseDao<IncomeType> {
    Optional<IncomeType> findByDescription(String description);
    List<IncomeType> listByUser(User user);
}
