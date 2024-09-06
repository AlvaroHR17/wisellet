package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.dao.*;
import com.alvarohdr.gastosapi.domain.model.*;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v2/updateVariableExpense")
public class UpdateVariableExpenseController {
    private final VariableExpenseDao variableExpenseDao;
    private final VariableExpenseTypeDao variableExpenseTypeDao;
    private final UserService userService;

    @Autowired
    public UpdateVariableExpenseController(VariableExpenseDao variableExpenseDao, VariableExpenseTypeDao variableExpenseTypeDao, UserService userService) {
        this.variableExpenseDao = variableExpenseDao;
        this.variableExpenseTypeDao = variableExpenseTypeDao;
        this.userService = userService;
    }

    @PutMapping
    public void update(@RequestBody UpdateTransactionCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());

        VariableExpense variableExpense = variableExpenseDao.getSecure(command.getId(), userId)
                .orElseThrow(() -> new RuntimeException("The variable expense with ID [" + command.getId() + "] doesn't exist for the user [" + userId + "]"));
        Optional<VariableExpenseType> optionalVariableExpenseType = variableExpenseTypeDao.findByDescription(command.getName(), userId);

        VariableExpenseType variableExpenseType, variableExpenseTypeToDelete = null;
        List<VariableExpense> variableExpenses = variableExpenseDao.listVariableExpensesByTypeDescription(variableExpense.getType().getDescription());

        if(optionalVariableExpenseType.isPresent()) {
            variableExpenseType = optionalVariableExpenseType.get();
            if(variableExpenses.size() <= 1) {
                variableExpenseTypeToDelete = variableExpense.getType();
            }
        } else {
            if(variableExpenses.size() > 1) {
                User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("The user with id [" + userId + "] doesn´t exist"));
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
