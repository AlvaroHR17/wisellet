package com.alvarohdr.gastosapi.domain.dto;

import java.io.Serializable;

public abstract class TransactionDto implements Serializable {
    private static final long serialVersionUID = -7285690601763723034L;

    protected long id;
    protected float amount;

    public TransactionDto() {
    }

    public TransactionDto(long id, float amount) {
        this.id = id;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
