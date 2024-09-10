package com.alvarohdr.gastosapi.story.createtransaction;

import java.io.Serializable;

public class CreateTransactionCommand implements Serializable {
    private static final long serialVersionUID = -2554841352738015890L;

    private String name;
    private float amount;
    private short month;
    private int year;

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

    public short getMonth() {
        return month;
    }

    public void setMonth(short month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
