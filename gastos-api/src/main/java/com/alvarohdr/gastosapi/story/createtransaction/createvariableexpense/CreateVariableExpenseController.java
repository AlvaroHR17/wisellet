package com.alvarohdr.gastosapi.story.createtransaction.createvariableexpense;

import com.alvarohdr.gastosapi.domain.dao.VariableExpenseDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import com.alvarohdr.gastosapi.story.createtransaction.CreateTransactionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v2/createVariableExpense")
public class CreateVariableExpenseController {
    private final VariableExpenseTypeDao variableExpenseTypeDao;
    private final UserService userService;
    private final VariableExpenseDao variableExpenseDao;

    @Autowired
    public CreateVariableExpenseController(VariableExpenseTypeDao variableExpenseTypeDao,
                                           UserService userService,
                                           VariableExpenseDao variableExpenseDao) {
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.userService = userService;
        this.variableExpenseDao = variableExpenseDao;
    }

    @PostMapping
    public void create(@RequestBody CreateTransactionCommand command) {
        User user = userService.getCurrentUser().orElseThrow(() -> new RuntimeException("Unauthorized access: Authentication is required to access this resource"));

        Optional<VariableExpenseType> optionalVariableExpenseType = variableExpenseTypeDao.findByDescription(command.getName());

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
        variableExpense.setCreationDate(LocalDateTime.now());
        variableExpense.setType(variableExpenseType);
        variableExpense.setAmount(command.getAmount());
        variableExpense.setMonth(command.getMonth());
        variableExpense.setYear(command.getYear());
        variableExpenseDao.saveOrUpdate(variableExpense);
    }
}
