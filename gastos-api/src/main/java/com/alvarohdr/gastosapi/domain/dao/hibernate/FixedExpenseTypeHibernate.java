package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.FixedExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.FixedExpenseType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FixedExpenseTypeHibernate extends BaseDaoImpl<FixedExpenseType> implements FixedExpenseTypeDao {
    @Override
    public Optional<FixedExpenseType> findByDescription(String description, long userId) {
        String query = FROM + " fety where fety.description = :description and fety.user.id = :userId";
        return getSession().createQuery(query, FixedExpenseType.class)
                .setParameter("description", description)
                .setParameter("userId", userId)
                .uniqueResultOptional();
    }
}
