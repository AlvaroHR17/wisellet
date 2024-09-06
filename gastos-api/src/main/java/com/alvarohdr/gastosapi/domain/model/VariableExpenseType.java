package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "VARIABLE_EXPENSE_TYPES")
public class VariableExpenseType extends SecuredAbstractEntity {

    private static final long serialVersionUID = -3822223935437006429L;
    private static final int DESCRIPTION_LENGTH = 255;

    private String description;

    public VariableExpenseType() {
    }

    public VariableExpenseType(long id, User user, String description) {
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

}
