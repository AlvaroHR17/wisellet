package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.dao.*;
import com.alvarohdr.gastosapi.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("updateVariableExpense")
public class UpdateVariableExpenseController {
    private final VariableExpenseDao variableExpenseDao;
    private final VariableExpenseTypeDao variableExpenseTypeDao;
    private final UserDao userDao;

    @Autowired
    public UpdateVariableExpenseController(VariableExpenseDao variableExpenseDao, VariableExpenseTypeDao variableExpenseTypeDao, UserDao userDao) {
        this.variableExpenseDao = variableExpenseDao;
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.userDao = userDao;
    }

    @PutMapping
    public void update(@RequestBody UpdateTransactionCommand command) {
        VariableExpense variableExpense = variableExpenseDao.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("The variable expense with ID [" + command.getId() + "] doesn't exist"));
        Optional<VariableExpenseType> optionalVariableExpenseType = variableExpenseTypeDao.findByDescription(command.getName());

        VariableExpenseType variableExpenseType, variableExpenseTypeToDelete = null;
        List<VariableExpense> variableExpenses = variableExpenseDao.listVariableExpensesByTypeDescription(variableExpense.getType().getDescription());

        if(optionalVariableExpenseType.isPresent()) {
            variableExpenseType = optionalVariableExpenseType.get();
            if(variableExpenses.size() <= 1) {
                variableExpenseTypeToDelete = variableExpense.getType();
            }
        } else {
            if(variableExpenses.size() > 1) {
                User user = userDao.findByUsername("Alvaro").orElseThrow(() -> new RuntimeException("El usuario no existe"));
                variableExpenseType = new VariableExpenseType();
                variableExpenseType.setUser(user);
            } else {
                variableExpenseType = variableExpense.getType();
            }
            variableExpenseType.setDescription(command.getName());
            variableExpenseTypeDao.saveOrUpdate(variableExpenseType);
        }

        variableExpense.setType(variableExpenseType);
        variableExpense.setAmount(command.getAmount());
        variableExpenseDao.saveOrUpdate(variableExpense);

        if(Objects.nonNull(variableExpenseTypeToDelete)) {
            variableExpenseTypeDao.delete(variableExpenseTypeToDelete);
        }
    }
}
