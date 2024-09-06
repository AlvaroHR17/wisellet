package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VariableExpenseTypeHibernate extends BaseDaoImpl<VariableExpenseType> implements VariableExpenseTypeDao {
    @Override
    public Optional<VariableExpenseType> findByDescription(String description, long userId) {
        String query = FROM + " vety where vety.description = :description and vety.user.id = :userId";
        return getSession().createQuery(query, VariableExpenseType.class)
                .setParameter("description", description)
                .setParameter("userId", userId)
                .uniqueResultOptional();
    }
}
