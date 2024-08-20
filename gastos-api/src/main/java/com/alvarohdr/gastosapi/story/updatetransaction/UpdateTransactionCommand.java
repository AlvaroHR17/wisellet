package com.alvarohdr.gastosapi.story.updatetransaction;

import java.io.Serializable;

public class UpdateTransactionCommand implements Serializable {
    private static final long serialVersionUID = -2554841352738015890L;

    private long id;

    private String name;

    private float amount;

    public UpdateTransactionCommand() {
    }

    public UpdateTransactionCommand(long id, String name, float amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
