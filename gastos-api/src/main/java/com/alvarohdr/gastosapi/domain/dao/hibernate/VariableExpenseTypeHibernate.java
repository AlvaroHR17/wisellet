package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VariableExpenseTypeHibernate extends BaseDaoImpl<VariableExpenseType> implements VariableExpenseTypeDao {
    @Override
    public Optional<VariableExpenseType> findByDescription(String description) {
        String query = FROM + " vety where vety.description = :description";
        return getSession().createQuery(query, VariableExpenseType.class)
                .setParameter("description", description)
                .uniqueResultOptional();
    }

    @Override
    public List<VariableExpenseType> listByUser(User user) {
        String query = FROM + " vety where vety.user = :user";
        return getSession().createQuery(query, VariableExpenseType.class)
                .setParameter("user", user)
                .list();
    }
}
