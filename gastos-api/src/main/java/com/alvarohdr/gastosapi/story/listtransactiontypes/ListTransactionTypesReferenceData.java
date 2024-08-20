package com.alvarohdr.gastosapi.story.listtransactiontypes;

import com.alvarohdr.gastosapi.domain.dto.FixedExpenseTypeDto;
import com.alvarohdr.gastosapi.domain.dto.IncomeTypeDto;
import com.alvarohdr.gastosapi.domain.dto.VariableExpenseTypeDto;

import java.io.Serializable;
import java.util.List;

public class ListTransactionTypesReferenceData implements Serializable {
    private static final long serialVersionUID = -7435872882318831175L;
    private List<IncomeTypeDto> incomeTypes;
    private List<FixedExpenseTypeDto> fixedExpenseTypes;
    private List<VariableExpenseTypeDto> variableExpenseTypes;

    public ListTransactionTypesReferenceData() {
    }

    public ListTransactionTypesReferenceData(List<IncomeTypeDto> incomeTypes,
                                             List<FixedExpenseTypeDto> fixedExpenseTypes,
                                             List<VariableExpenseTypeDto> variableExpenseTypes) {
        this.incomeTypes = incomeTypes;
        this.fixedExpenseTypes = fixedExpenseTypes;
        this.variableExpenseTypes = variableExpenseTypes;
    }

    public List<IncomeTypeDto> getIncomeTypes() {
        return incomeTypes;
    }

    public void setIncomeTypes(List<IncomeTypeDto> incomeTypes) {
        this.incomeTypes = incomeTypes;
    }

    public List<FixedExpenseTypeDto> getFixedExpenseTypes() {
        return fixedExpenseTypes;
    }

    public void setFixedExpenseTypes(List<FixedExpenseTypeDto> fixedExpenseTypes) {
        this.fixedExpenseTypes = fixedExpenseTypes;
    }

    public List<VariableExpenseTypeDto> getVariableExpenseTypes() {
        return variableExpenseTypes;
    }

    public void setVariableExpenseTypes(List<VariableExpenseTypeDto> variableExpenseTypes) {
        this.variableExpenseTypes = variableExpenseTypes;
    }
}
