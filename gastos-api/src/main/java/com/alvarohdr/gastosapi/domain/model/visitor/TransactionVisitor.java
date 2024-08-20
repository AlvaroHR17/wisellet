package com.alvarohdr.gastosapi.domain.model.visitor;

import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;

public interface TransactionVisitor<T> {
   T visit(Income income);

    T visit(FixedExpense fixedExpense);

    T visit(VariableExpense variableExpense);
}
