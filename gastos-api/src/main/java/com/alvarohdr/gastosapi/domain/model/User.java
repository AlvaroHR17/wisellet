package com.alvarohdr.gastosapi.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    private static final long serialVersionUID = 1375848602968175021L;
    private static final int USERNAME_LENGTH = 255;
    private static final int PASSWORD_LENGTH = 500;
    private static final int EMAIL_LENGTH = 255;

    private long id;
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

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
