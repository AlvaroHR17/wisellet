package com.alvarohdr.gastosapi.story.createvariableexpense;

import com.alvarohdr.gastosapi.domain.dao.UserDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("createVariableExpense")
public class CreateVariableExpenseController {
    private final VariableExpenseTypeDao variableExpenseTypeDao;
    private final UserDao userDao;
    private final VariableExpenseDao variableExpenseDao;

    @Autowired
    public CreateVariableExpenseController(VariableExpenseTypeDao variableExpenseTypeDao,
                                           UserDao userDao,
                                           VariableExpenseDao variableExpenseDao) {
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.userDao = userDao;
        this.variableExpenseDao = variableExpenseDao;
    }

    @PostMapping
    public void create(@RequestBody CreateVariableExpenseCommand command) {
        Optional<VariableExpenseType> optionalVariableExpenseType = variableExpenseTypeDao.findByDescription(command.getName());
        // find user session?
        User user = userDao.findByUsername("Alvaro").orElseThrow(() -> new RuntimeException("El usuario no existe"));
        VariableExpenseType variableExpenseType;
        if(optionalVariableExpenseType.isPresent()) {
            variableExpenseType = optionalVariableExpenseType.get();
        } else {
            variableExpenseType = new VariableExpenseType();
            variableExpenseType.setDescription(command.getName());
            variableExpenseType.setUser(user);
            variableExpenseTypeDao.saveOrUpdate(variableExpenseType);
        }

        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setUser(user);
        variableExpense.setCreationDate(LocalDate.now());
        variableExpense.setType(variableExpenseType);
        variableExpense.setAmount(command.getAmount());
        variableExpenseDao.saveOrUpdate(variableExpense);
    }
}
