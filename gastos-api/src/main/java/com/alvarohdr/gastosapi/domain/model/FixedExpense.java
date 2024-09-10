package com.alvarohdr.gastosapi.domain.model;

import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "FIXED_EXPENSES")
public class FixedExpense extends Transaction{
    private static final long serialVersionUID = 4012407683010280868L;
    private FixedExpenseType type;

    public FixedExpense() {
        super();
    }

    @Override
    public <T> T accept(TransactionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public FixedExpense(long id,
                        User user,
                        int amount,
                        short month,
                        int year,
                        LocalDateTime creationDate,
                        FixedExpenseType type) {
        super(id, user, amount, month, year, creationDate);
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIXED_EXPENSES_TYPE_ID", nullable = false)
    public FixedExpenseType getType() {
        return type;
    }

    public void setType(FixedExpenseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FixedExpense{" +
                "type=" + type +
                ", id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                '}';
    }
}
