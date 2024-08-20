package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.FixedExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpenseType;
import com.alvarohdr.gastosapi.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FixedExpenseTypeHibernate extends BaseDaoImpl<FixedExpenseType> implements FixedExpenseTypeDao {
    @Override
    public Optional<FixedExpenseType> findByDescription(String description) {
        String query = FROM + " fety where fety.description = :description";
        return getSession().createQuery(query, FixedExpenseType.class)
                .setParameter("description", description)
                .uniqueResultOptional();
    }

    @Override
    public List<FixedExpenseType> listByUser(User user) {
        String query = FROM + " fety where fety.user = :user";
        return getSession().createQuery(query, FixedExpenseType.class)
                .setParameter("user", user)
                .list();
    }
}
