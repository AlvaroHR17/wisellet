package com.alvarohdr.gastosapi.domain.dto;

public class IncomeDto extends TransactionDto{
    private static final long serialVersionUID = 4597400023906625133L;

    private IncomeTypeDto type;

    public IncomeDto() {
    }

    public IncomeDto(long id,
                     float amount,
                     IncomeTypeDto type) {
        super(id, amount);
        this.type = type;
    }

    public IncomeTypeDto getType() {
        return type;
    }

    public void setType(IncomeTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IncomeDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
