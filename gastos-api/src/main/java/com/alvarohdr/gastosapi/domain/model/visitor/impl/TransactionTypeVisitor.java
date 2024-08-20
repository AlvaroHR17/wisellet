package com.alvarohdr.gastosapi.domain.model.visitor.impl;

import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.enums.EnumTransactionTypes;
import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;

public class TransactionTypeVisitor implements TransactionVisitor<EnumTransactionTypes> {
    @Override
    public EnumTransactionTypes visit(Income income) {
        return EnumTransactionTypes.INCOME;
    }

    @Override
    public EnumTransactionTypes visit(FixedExpense fixedExpense) {
        return EnumTransactionTypes.FIXED_EXPENSE;
    }

    @Override
    public EnumTransactionTypes visit(VariableExpense variableExpense) {
        return EnumTransactionTypes.VARIABLE_EXPENSE;
    }
}
