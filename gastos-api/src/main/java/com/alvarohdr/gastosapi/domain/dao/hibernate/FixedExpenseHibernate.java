package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.FixedExpenseDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedExpenseHibernate extends BaseDaoImpl<FixedExpense> implements FixedExpenseDao {

    @Override
    public List<FixedExpense> listFixedExpensesByTypeDescription(String typeDescription) {
        String query = FROM + " fixedExpense where fixedExpense.type.description = :typeDescription";
        return getSession().createQuery(query, FixedExpense.class)
                .setParameter("typeDescription", typeDescription)
                .list();
    }
}
