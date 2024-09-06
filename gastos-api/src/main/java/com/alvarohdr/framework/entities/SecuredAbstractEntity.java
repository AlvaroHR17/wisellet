package com.alvarohdr.framework.entities;

import com.alvarohdr.gastosapi.domain.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SecuredAbstractEntity extends AbstractEntity {
    private static final long serialVersionUID = 5004702995411155481L;

    protected User user;

    public SecuredAbstractEntity() {
    }

    public SecuredAbstractEntity(long id, User user) {
        super(id);
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
