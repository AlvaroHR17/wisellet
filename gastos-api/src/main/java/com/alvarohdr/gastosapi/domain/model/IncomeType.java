package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "INCOME_TYPES")
public class IncomeType extends SecuredAbstractEntity {
    private static final long serialVersionUID = -7306350981018138372L;

    private static final int DESCRIPTION_LENGTH = 255;

    private String description;
    private User user;

    public IncomeType() {
    }

    public IncomeType(long id, User user, String description) {
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
