package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IncomeTypeHibernate extends BaseDaoImpl<IncomeType> implements IncomeTypeDao {
    @Override
    public Optional<IncomeType> findByDescription(String description) {
        String query = FROM + " inty where inty.description = :description";
        return getSession().createQuery(query, IncomeType.class)
                .setParameter("description", description)
                .uniqueResultOptional();
    }

    @Override
    public List<IncomeType> listByUser(User user) {
        String query = FROM + " inty where inty.user = :user";
        return getSession().createQuery(query, IncomeType.class)
                .setParameter("user", user)
                .list();
    }
}
