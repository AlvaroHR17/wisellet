package com.alvarohdr.gastosapi.domain.dao;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.gastosapi.domain.model.Income;

import java.util.List;

public interface IncomeDao extends BaseDao<Income> {
    List<Income> listIncomesByTypeDescription(String typeDescription);
}
