package com.alvarohdr.gastosapi.domain.dto;

public class FixedExpenseDto extends TransactionDto{
    private static final long serialVersionUID = 4597400023906625133L;

    private FixedExpenseTypeDto type;

    public FixedExpenseDto() {
    }

    public FixedExpenseDto(long id,
                           float amount,
                           FixedExpenseTypeDto type) {
        super(id, amount);
        this.type = type;
    }

    public FixedExpenseTypeDto getType() {
        return type;
    }

    public void setType(FixedExpenseTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FixedExpenseType{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
