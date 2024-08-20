package com.alvarohdr.gastosapi.story.showtransactions;

import com.alvarohdr.gastosapi.domain.dto.FixedExpenseDto;
import com.alvarohdr.gastosapi.domain.dto.IncomeDto;
import com.alvarohdr.gastosapi.domain.dto.VariableExpenseDto;

import java.io.Serializable;
import java.util.List;

public class ShowTransactionsReferenceData implements Serializable {
    private static final long serialVersionUID = -6010688797001130554L;

    private List<IncomeDto> incomes;
    private List<FixedExpenseDto> fixedExpenses;
    private List<VariableExpenseDto> variableExpenses;

    public ShowTransactionsReferenceData() {
    }

    public ShowTransactionsReferenceData(List<IncomeDto> incomes,
                                         List<FixedExpenseDto> fixedExpenses,
                                         List<VariableExpenseDto> variableExpenses) {
        this.incomes = incomes;
        this.fixedExpenses = fixedExpenses;
        this.variableExpenses = variableExpenses;
    }

    public List<IncomeDto> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeDto> incomes) {
        this.incomes = incomes;
    }

    public List<FixedExpenseDto> getFixedExpenses() {
        return fixedExpenses;
    }

    public void setFixedExpenses(List<FixedExpenseDto> fixedExpenses) {
        this.fixedExpenses = fixedExpenses;
    }

    public List<VariableExpenseDto> getVariableExpenses() {
        return variableExpenses;
    }

    public void setVariableExpenses(List<VariableExpenseDto> variableExpenses) {
        this.variableExpenses = variableExpenses;
    }

    @Override
    public String toString() {
        return "ShowTransactionsReferenceData{" +
                "incomes=" + incomes +
                ", fixedExpenses=" + fixedExpenses +
                ", variableExpenses=" + variableExpenses +
                '}';
    }
}
