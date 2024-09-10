package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "VARIABLE_EXPENSES")
public class VariableExpense extends Transaction{
    private static final long serialVersionUID = -8014568966361624359L;
    private VariableExpenseType type;

    public VariableExpense() {
        super();
    }

    @Override
    public <T> T accept(TransactionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public VariableExpense(long id,
                           User user,
                           int amount,
                           short month,
                           int year,
                           LocalDateTime creationDate,
                           VariableExpenseType type) {
        super(id, user, amount, month, year, creationDate);
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VARIABLE_EXPENSES_TYPE_ID", nullable = false)
    public VariableExpenseType getType() {
        return type;
    }

    public void setType(VariableExpenseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VariableExpense{" +
                "type=" + type +
                ", id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
