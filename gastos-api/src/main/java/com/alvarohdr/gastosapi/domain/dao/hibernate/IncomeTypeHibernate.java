package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IncomeTypeHibernate extends BaseDaoImpl<IncomeType> implements IncomeTypeDao {
    @Override
    public Optional<IncomeType> findByDescription(String description, long userId) {
        String query = FROM + " inty where inty.description = :description and inty.user.id = :userId";
        return getSession().createQuery(query, IncomeType.class)
                .setParameter("description", description)
                .setParameter("userId", userId)
                .uniqueResultOptional();
    }
}
