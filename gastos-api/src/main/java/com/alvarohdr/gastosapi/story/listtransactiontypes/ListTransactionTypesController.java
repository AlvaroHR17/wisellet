package com.alvarohdr.gastosapi.story.listtransactiontypes;

import com.alvarohdr.gastosapi.domain.dao.FixedExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.dto.FixedExpenseTypeDto;
import com.alvarohdr.gastosapi.domain.dto.IncomeTypeDto;
import com.alvarohdr.gastosapi.domain.dto.VariableExpenseTypeDto;
import com.alvarohdr.gastosapi.domain.factory.FixedExpenseTypeFactory;
import com.alvarohdr.gastosapi.domain.factory.IncomeTypeFactory;
import com.alvarohdr.gastosapi.domain.factory.VariableExpenseTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/listTransactionTypes")
public class ListTransactionTypesController {

    private final IncomeTypeDao incomeTypeDao;
    private final FixedExpenseTypeDao fixedExpenseTypeDao;
    private final VariableExpenseTypeDao variableExpenseTypeDao;

    private final IncomeTypeFactory incomeTypeFactory;
    private final FixedExpenseTypeFactory fixedExpenseTypeFactory;
    private final VariableExpenseTypeFactory variableExpenseTypeFactory;

    @Autowired
    public ListTransactionTypesController(IncomeTypeDao incomeTypeDao,
                                          FixedExpenseTypeDao fixedExpenseTypeDao,
                                          VariableExpenseTypeDao variableExpenseTypeDao,
                                          IncomeTypeFactory incomeTypeFactory,
                                          FixedExpenseTypeFactory fixedExpenseTypeFactory,
                                          VariableExpenseTypeFactory variableExpenseTypeFactory) {
        this.incomeTypeDao = incomeTypeDao;
        this.fixedExpenseTypeDao = fixedExpenseTypeDao;
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.incomeTypeFactory = incomeTypeFactory;
        this.fixedExpenseTypeFactory = fixedExpenseTypeFactory;
        this.variableExpenseTypeFactory = variableExpenseTypeFactory;
    }

    @GetMapping
    public ListTransactionTypesReferenceData list() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());

        List<IncomeTypeDto> incomeTypes = incomeTypeDao.findAllSecure(userId).stream()
                .map(incomeTypeFactory::getDto)
                .collect(Collectors.toList());

        List<FixedExpenseTypeDto> fixedExpenseTypes = fixedExpenseTypeDao.findAllSecure(userId).stream()
                .map(fixedExpenseTypeFactory::getDto)
                .collect(Collectors.toList());

        List<VariableExpenseTypeDto> variableExpenseTypes = variableExpenseTypeDao.findAllSecure(userId).stream()
                .map(variableExpenseTypeFactory::getDto)
                .collect(Collectors.toList());

        return new ListTransactionTypesReferenceData(incomeTypes, fixedExpenseTypes, variableExpenseTypes);
    }
}
