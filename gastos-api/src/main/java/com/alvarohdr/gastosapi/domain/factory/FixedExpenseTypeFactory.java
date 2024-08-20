package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.FixedExpenseTypeDto;
import com.alvarohdr.gastosapi.domain.model.FixedExpenseType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FixedExpenseTypeFactory {
    public FixedExpenseTypeDto getDto(FixedExpenseType fixedExpenseType) {
        return new FixedExpenseTypeDto(fixedExpenseType.getId(), fixedExpenseType.getDescription());
    }

    public List<FixedExpenseTypeDto> getDtoList(List<FixedExpenseType> fixedExpenseTypes) {
        return fixedExpenseTypes.stream().map(this::getDto).collect(Collectors.toList());
    }
}
