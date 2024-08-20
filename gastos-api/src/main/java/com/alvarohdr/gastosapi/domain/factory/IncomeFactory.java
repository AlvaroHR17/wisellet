package com.alvarohdr.gastosapi.domain.factory;

import com.alvarohdr.gastosapi.domain.dto.IncomeDto;
import com.alvarohdr.gastosapi.domain.model.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncomeFactory {

    private final IncomeTypeFactory incomeTypeFactory;
    @Autowired
    public IncomeFactory(IncomeTypeFactory incomeTypeFactory) {
        this.incomeTypeFactory = incomeTypeFactory;
    }

    public IncomeDto getDto(Income income) {
        return new IncomeDto(income.getId(),
                income.getAmount(),
                incomeTypeFactory.getDto(income.getType()));
    }

    public List<IncomeDto> getDtoList(List<Income> incomes) {
        return incomes.stream().map(this::getDto).collect(Collectors.toList());
    }
}
