package com.alvarohdr.gastosapi.story.showtransactions;

import com.alvarohdr.gastosapi.domain.dto.FixedExpenseDto;
import com.alvarohdr.gastosapi.domain.dto.IncomeDto;
import com.alvarohdr.gastosapi.domain.dto.VariableExpenseDto;
import com.alvarohdr.gastosapi.domain.factory.FixedExpenseFactory;
import com.alvarohdr.gastosapi.domain.factory.IncomeFactory;
import com.alvarohdr.gastosapi.domain.factory.VariableExpenseFactory;
import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.enums.EnumTransactionTypes;
import com.alvarohdr.gastosapi.domain.model.visitor.impl.TransactionTypeVisitor;
import com.alvarohdr.gastosapi.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/showTransactions")
public class ShowTransactionsController {
    private final TransactionService transactionService;

    private final IncomeFactory incomeFactory;
    private final FixedExpenseFactory fixedExpenseFactory;
    private final VariableExpenseFactory variableExpenseFactory;

    @Autowired
    public ShowTransactionsController(TransactionService transactionService,
                                      IncomeFactory incomeFactory,
                                      FixedExpenseFactory fixedExpenseFactory,
                                      VariableExpenseFactory variableExpenseFactory) {
        this.transactionService = transactionService;
        this.incomeFactory = incomeFactory;
        this.fixedExpenseFactory = fixedExpenseFactory;
        this.variableExpenseFactory = variableExpenseFactory;
    }

    @GetMapping
    public ShowTransactionsReferenceData list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());

        TransactionTypeVisitor visitor = new TransactionTypeVisitor();
        List<Transaction> transactions = transactionService.listTransactionsByUser(userId);

        List<IncomeDto> incomes = transactions.stream()
                .filter(transaction -> transaction.accept(visitor).equals(EnumTransactionTypes.INCOME))
                .map(t-> incomeFactory.getDto((Income) t))
                .toList();
        List<FixedExpenseDto> fixedExpenses = transactions.stream()
                .filter(transaction -> transaction.accept(visitor).equals(EnumTransactionTypes.FIXED_EXPENSE))
                .map(t-> fixedExpenseFactory.getDto((FixedExpense) t))
                .toList();
        List<VariableExpenseDto> variableExpenses = transactions.stream()
                .filter(transaction -> transaction.accept(visitor).equals(EnumTransactionTypes.VARIABLE_EXPENSE))
                .map(t-> variableExpenseFactory.getDto((VariableExpense) t))
                .toList();

        return new ShowTransactionsReferenceData(incomes, fixedExpenses, variableExpenses);
    }
}
