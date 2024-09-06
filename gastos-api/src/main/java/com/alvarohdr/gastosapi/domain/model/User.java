package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.AbstractEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {
    private static final long serialVersionUID = 1375848602968175021L;

    private String firstName;

    private String lastName;

    private String email;

    private String roles;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Column(name = "FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ROLES", nullable = false)
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
