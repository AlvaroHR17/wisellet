package com.alvarohdr.framework.dao.hibernate;

import com.alvarohdr.framework.dao.BaseDao;
import jakarta.transaction.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDaoImpl<T extends Object> extends AbstractDaoSupport implements BaseDao<T> {

    private final Class<T> clazz;
    protected final String FROM;

    protected BaseDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.FROM = "FROM " + getClazz().getName();
    }

    @Transactional
    public void saveOrUpdate(T object) {
        em.persist(object);
        em.flush();
    }

    public List<T> findAll() {
        return getSession().createQuery(FROM, this.getClazz())
                .list();
    }

    @Transactional
    public void delete(T object) {
        em.remove(object);
        em.flush();
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
