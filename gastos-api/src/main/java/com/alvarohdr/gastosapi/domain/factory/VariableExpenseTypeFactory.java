package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.VariableExpenseTypeDto;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariableExpenseTypeFactory {
    public VariableExpenseTypeDto getDto(VariableExpenseType variableExpenseType) {
        return new VariableExpenseTypeDto(variableExpenseType.getId(), variableExpenseType.getDescription());
    }

    public List<VariableExpenseTypeDto> getDtoList(List<VariableExpenseType> variableExpenseTypes) {
        return variableExpenseTypes.stream().map(this::getDto).collect(Collectors.toList());
    }
}
