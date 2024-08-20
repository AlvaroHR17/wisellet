package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "INCOMES")
public class Income extends Transaction{
    private static final long serialVersionUID = -1525372478061649382L;
    private IncomeType type;

    public Income() {
        super();
    }

    @Override
    public <T> T accept(TransactionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Income(long id,
                  User user,
                  int amount,
                  LocalDate creationDate,
                  IncomeType type) {
        super(id, user, amount, creationDate);
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INCOME_TYPE_ID", nullable = false)
    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Income{" +
                "type=" + type +
                ", id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
