package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeDao extends BaseDao<Income> {
    Optional<Income> findById(long id);

    List<Income> listIncomesByTypeDescription(String typeDescription);
}
