package com.alvarohdr.framework.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2071490602328797190L;

    protected long id;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
