package com.alvarohdr.gastosapi.domain.dao.hibernate;

import com.alvarohdr.framework.dao.hibernate.BaseDaoImpl;
import com.alvarohdr.gastosapi.domain.dao.UserDao;
import com.alvarohdr.gastosapi.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHibernate extends BaseDaoImpl<User> implements UserDao {
    @Override
    public Class<User> getClazz() {
        return User.class;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = FROM + " user where user.username = :username";
        return getSession().createQuery(query, User.class)
                .setParameter("username", username)
                .uniqueResultOptional();
    }
}
