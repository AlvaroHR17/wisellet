package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IncomeHibernate extends BaseDaoImpl<Income> implements IncomeDao {


    @Override
    public Optional<Income> findById(long id) {
        String query = FROM + " income where income.id = :id";
        return getSession().createQuery(query, Income.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public List<Income> listIncomesByTypeDescription(String typeDescription) {
        String query = FROM + " income where income.type.description = :typeDescription";
        return getSession().createQuery(query, Income.class)
                .setParameter("typeDescription", typeDescription)
                .list();
    }
}
