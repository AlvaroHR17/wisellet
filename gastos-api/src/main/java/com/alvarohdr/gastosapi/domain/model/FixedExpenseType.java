package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "FIXED_EXPENSE_TYPES")
public class FixedExpenseType extends SecuredAbstractEntity {

    private static final long serialVersionUID = -7525644238064195439L;
    private static final int DESCRIPTION_LENGTH = 255;

    private String description;
    private User user;

    public FixedExpenseType() {
    }

    public FixedExpenseType(long id, User user, String description) {
        super(id, user);
        this.description = description;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FixedExpenseType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
