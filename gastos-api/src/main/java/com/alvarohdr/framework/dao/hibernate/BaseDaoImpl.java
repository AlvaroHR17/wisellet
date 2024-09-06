package com.alvarohdr.framework.dao.hibernate;

import com.alvarohdr.framework.dao.BaseDao;
import jakarta.transaction.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

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

    public List<T> findAllSecure(long userId) {
        return getSession().createQuery(FROM + " entity where entity.user.id = :userId", this.getClazz())
                .setParameter("userId", userId)
                .list();
    }

    public Optional<T> get(long id) {
        return getSession().createQuery(FROM + " entity where entity.id = :id", this.getClazz())
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    public Optional<T> getSecure(long id, long userId) {
        return getSession().createQuery(FROM + " entity where entity.id = :id and entity.user.id = :userId", this.getClazz())
                .setParameter("id", id)
                .setParameter("userId", userId)
                .uniqueResultOptional();
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
