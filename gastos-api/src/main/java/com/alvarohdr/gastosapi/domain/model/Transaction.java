package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRANSACTIONS")
public abstract class Transaction extends SecuredAbstractEntity {
    private static final long serialVersionUID = -5469930019104134853L;

    protected float amount;
    protected LocalDate creationDate;

    public Transaction() {
    }

    public Transaction(long id,
                       User user,
                       int amount,
                       LocalDate creationDate) {
        super(id, user);
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public abstract <T> T accept(TransactionVisitor<T> visitor);

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
