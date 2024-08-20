package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.FixedExpenseDto;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FixedExpenseFactory {

    private final FixedExpenseTypeFactory fixedExpenseTypeFactory;
    @Autowired
    public FixedExpenseFactory(FixedExpenseTypeFactory fixedExpenseTypeFactory) {
        this.fixedExpenseTypeFactory = fixedExpenseTypeFactory;
    }

    public FixedExpenseDto getDto(FixedExpense fixedExpense) {
        return new FixedExpenseDto(fixedExpense.getId(),
                fixedExpense.getAmount(),
                fixedExpenseTypeFactory.getDto(fixedExpense.getType()));
    }

    public List<FixedExpenseDto> getDtoList(List<FixedExpense> fixedExpenses) {
        return fixedExpenses.stream().map(this::getDto).collect(Collectors.toList());
    }
}
