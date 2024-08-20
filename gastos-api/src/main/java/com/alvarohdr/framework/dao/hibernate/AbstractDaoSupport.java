package com.alvarohdr.framework.dao.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;

public abstract class AbstractDaoSupport {

    @PersistenceContext
    private Session session;

    @PersistenceContext
    protected EntityManager em;

    protected Session getSession() {
        return this.session;
    }


}
