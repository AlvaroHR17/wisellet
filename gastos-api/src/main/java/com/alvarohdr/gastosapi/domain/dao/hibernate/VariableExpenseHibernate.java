package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseDao;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VariableExpenseHibernate extends BaseDaoImpl<VariableExpense> implements VariableExpenseDao {

    @Override
    public Optional<VariableExpense> findById(long id) {
        String query = FROM + " variableExpense where variableExpense.id = :id";
        return getSession().createQuery(query, VariableExpense.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public List<VariableExpense> listVariableExpensesByTypeDescription(String typeDescription) {
        String query = FROM + " variableExpense where variableExpense.type.description = :typeDescription";
        return getSession().createQuery(query, VariableExpense.class)
                .setParameter("typeDescription", typeDescription)
                .list();
    }
}
