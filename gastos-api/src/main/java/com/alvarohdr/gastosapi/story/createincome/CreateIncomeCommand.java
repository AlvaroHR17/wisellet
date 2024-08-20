package com.alvarohdr.gastosapi.story.createincome;

import java.io.Serializable;

public class CreateIncomeCommand implements Serializable {
    private static final long serialVersionUID = -2554841352738015890L;

    private String name;

    private float amount;

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
