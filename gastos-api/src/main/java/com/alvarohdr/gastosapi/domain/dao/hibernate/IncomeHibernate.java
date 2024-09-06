package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncomeHibernate extends BaseDaoImpl<Income> implements IncomeDao {

    @Override
    public List<Income> listIncomesByTypeDescription(String typeDescription) {
        String query = FROM + " income where income.type.description = :typeDescription and income.user.id = :userId";
        return getSession().createQuery(query, Income.class)
                .setParameter("typeDescription", typeDescription)
                .setParameter("userId", getUserId())
                .list();
    }
}
