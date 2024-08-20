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
    private String username;
    // TODO: Encode password
    private String password;
    private String email;

    public User() {
    }

    public User(long id,
                String username,
                String password,
                String email) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    @Column(name = "USERNAME", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = PASSWORD_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
