package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.VariableExpenseDto;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariableExpenseFactory {

    private final VariableExpenseTypeFactory variableExpenseTypeFactory;
    @Autowired
    public VariableExpenseFactory(VariableExpenseTypeFactory variableExpenseTypeFactory) {
        this.variableExpenseTypeFactory = variableExpenseTypeFactory;
    }

    public VariableExpenseDto getDto(VariableExpense variableExpense) {
        return new VariableExpenseDto(variableExpense.getId(),
                variableExpense.getAmount(),
                variableExpenseTypeFactory.getDto(variableExpense.getType()));
    }

    public List<VariableExpenseDto> getDtoList(List<VariableExpense> variableExpenses) {
        return variableExpenses.stream().map(this::getDto).collect(Collectors.toList());
    }
}
