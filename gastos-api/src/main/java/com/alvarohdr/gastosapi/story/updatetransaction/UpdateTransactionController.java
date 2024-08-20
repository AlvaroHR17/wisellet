package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.model.FixedExpense;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.visitor.TransactionVisitor;
import com.alvarohdr.gastosapi.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("updateTransaction")
public class UpdateTransactionController {

    private final TransactionService transactionService;

    @Autowired
    public UpdateTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping
    public ModelAndView update(@RequestBody UpdateTransactionCommand command, Model model) {
        Transaction transaction = transactionService.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("The transaction with ID [" + command.getId() + "] doesn't exist"));

        model.addAttribute("command", command);

        UpdateTransactionVisitor visitor = new UpdateTransactionVisitor();
        return new ModelAndView(transaction.accept(visitor), model.asMap());
    }

    static class UpdateTransactionVisitor implements TransactionVisitor<String> {

        @Override
        public String visit(Income income) {
            return "redirect:/updateIncome";
        }

        @Override
        public String visit(FixedExpense fixedExpense) {
            return "redirect:/updateFixedExpense";
        }

        @Override
        public String visit(VariableExpense variableExpense) {
            return "redirect:/updateVariableExpense";
        }
    }
}