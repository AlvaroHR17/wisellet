package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.TransactionDao;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionHibernate extends BaseDaoImpl<Transaction> implements TransactionDao {
    @Override
    public Optional<Transaction> findById(long id) {
        String query = FROM + " transaction where transaction.id = :id";
        return getSession().createQuery(query, Transaction.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }
}
