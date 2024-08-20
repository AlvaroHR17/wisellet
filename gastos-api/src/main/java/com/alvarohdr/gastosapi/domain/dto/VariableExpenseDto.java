package com.alvarohdr.gastosapi.domain.dto;

public class VariableExpenseDto extends TransactionDto{
    private static final long serialVersionUID = 4597400023906625133L;

    private VariableExpenseTypeDto type;

    public VariableExpenseDto() {
    }

    public VariableExpenseDto(long id,
                              float amount,
                              VariableExpenseTypeDto type) {
        super(id, amount);
        this.type = type;
    }

    public VariableExpenseTypeDto getType() {
        return type;
    }

    public void setType(VariableExpenseTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VariableExpenseTypeDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
