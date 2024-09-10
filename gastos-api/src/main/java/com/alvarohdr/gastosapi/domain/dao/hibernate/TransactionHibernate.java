package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.TransactionDao;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<Transaction> listInMonth(short month, int year) {
        String query = FROM + " transaction where transaction.month = :month" +
                " and transaction.year = :year" +
                " and transaction.user.id = :userId";
        return getSession().createQuery(query, Transaction.class)
                .setParameter("month", month)
                .setParameter("year", year)
                .setParameter("userId", getUserId())
                .list();
    }
}
