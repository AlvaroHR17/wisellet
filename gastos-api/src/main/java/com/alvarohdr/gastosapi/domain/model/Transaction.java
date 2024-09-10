package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.framework.entities.SecuredAbstractEntity;
import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TRANSACTIONS")
public abstract class Transaction extends SecuredAbstractEntity {
    private static final long serialVersionUID = -5469930019104134853L;

    protected float amount;
    protected short month;
    protected int year;
    protected LocalDateTime creationDate;

    public Transaction() {
    }

    public Transaction(long id,
                       User user,
                       int amount,
                       short month,
                       int year,
                       LocalDateTime creationDate) {
        super(id, user);
        this.amount = amount;
        this.month = month;
        this.year = year;
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

    @Column(name = "MONTH", nullable = false)
    public short getMonth() {
        return month;
    }

    public void setMonth(short month) {
        this.month = month;
    }

    @Column(name = "YEAR", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
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
