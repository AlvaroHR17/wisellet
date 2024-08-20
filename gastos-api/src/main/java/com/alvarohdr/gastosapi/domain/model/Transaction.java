package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRANSACTIONS")
public abstract class Transaction implements Serializable {
    private static final long serialVersionUID = -5469930019104134853L;

    protected long id;
    protected User user;
    protected float amount;
    protected LocalDate creationDate;

    public Transaction() {
    }

    public Transaction(long id,
                       User user,
                       int amount,
                       LocalDate creationDate) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public abstract <T> T accept(TransactionVisitor<T> visitor);

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "AMOUNT", nullable = false)
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
