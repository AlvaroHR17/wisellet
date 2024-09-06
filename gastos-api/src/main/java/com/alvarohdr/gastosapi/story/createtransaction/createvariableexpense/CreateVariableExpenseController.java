package com.alvarohdr.gastosapi.story.createtransaction.createvariableexpense;

import com.alvarohdr.gastosapi.domain.dao.VariableExpenseDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
    public void create(@RequestBody CreateVariableExpenseCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("The user with id {" + userId + "} doesn´t exist"));

        Optional<VariableExpenseType> optionalVariableExpenseType = variableExpenseTypeDao.findByDescription(command.getName(), userId);

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
