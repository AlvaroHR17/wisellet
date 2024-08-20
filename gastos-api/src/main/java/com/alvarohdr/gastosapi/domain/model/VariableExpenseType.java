package com.alvarohdr.gastosapi.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "VARIABLE_EXPENSE_TYPES")
public class VariableExpenseType implements Serializable {

    private static final long serialVersionUID = -3822223935437006429L;
    private static final int DESCRIPTION_LENGTH = 255;

    private long id;
    private String description;
    private User user;

    public VariableExpenseType() {
    }

    public VariableExpenseType(long id, String description) {
        this.id = id;
        this.description = description;
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

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "VariableExpenseType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
