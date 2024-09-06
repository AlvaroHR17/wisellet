package com.alvarohdr.framework.dao.hibernate;

import com.alvarohdr.framework.dao.BaseDao;
import com.alvarohdr.framework.entities.AbstractEntity;
import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseDaoImpl<T extends AbstractEntity> extends AbstractDaoSupport implements BaseDao<T> {

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
        if(SecuredAbstractEntity.class.isAssignableFrom(getClazz()) && isUnauthorized()){
            return findAllSecure();
        }

        return getSession().createQuery(FROM, this.getClazz())
                .list();
    }

    private List<T> findAllSecure() {
        return getSession().createQuery(FROM + " entity where entity.user.id = :userId", this.getClazz())
                .setParameter("userId", getUserId())
                .list();
    }

    public Optional<T> get(long id) {
        if(SecuredAbstractEntity.class.isAssignableFrom(getClazz()) && isUnauthorized()){
            return getSecure(id);
        }

        return getSession().createQuery(FROM + " entity where entity.id = :id", this.getClazz())
                .setParameter("id", id)
                .uniqueResultOptional();

    }

    private Optional<T> getSecure(long id) {
        return getSession().createQuery(FROM + " entity where entity.id = :id and entity.user.id = :userId", this.getClazz())
                .setParameter("id", id)
                .setParameter("userId", getUserId())
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

    protected Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? Long.parseLong(authentication.getPrincipal().toString()) : null;
    }

    protected boolean isUnauthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.isNull(authentication) || Objects.isNull(authentication.getAuthorities())) return true;

        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"));
    }
}
