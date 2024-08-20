package com.alvarohdr.gastosapi.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum EnumTransactionTypes {
    INCOME ("INCOME", "income"),
    FIXED_EXPENSE("FIXED_EXPENSE", "Fixed expense"),
    VARIABLE_EXPENSE("VARIABLE_EXPENSE", "Variable expense");

    public String code;
    public String description;

    EnumTransactionTypes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EnumTransactionTypes> list() {
        return Arrays.asList(EnumTransactionTypes.values());
    }
}
