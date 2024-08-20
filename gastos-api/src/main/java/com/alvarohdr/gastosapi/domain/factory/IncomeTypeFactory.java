package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.IncomeTypeDto;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncomeTypeFactory {
    public IncomeTypeDto getDto(IncomeType incomeType) {
        return new IncomeTypeDto(incomeType.getId(), incomeType.getDescription());
    }

    public List<IncomeTypeDto> getDtoList(List<IncomeType> incomeType) {
        return incomeType.stream().map(this::getDto).collect(Collectors.toList());
    }
}
