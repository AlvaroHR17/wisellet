package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.IncomeType;

import java.util.Optional;

public interface IncomeTypeDao extends BaseDao<IncomeType> {
    Optional<IncomeType> findByDescription(String description, long userId);
}
