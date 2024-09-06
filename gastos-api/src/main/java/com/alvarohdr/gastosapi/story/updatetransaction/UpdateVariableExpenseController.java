package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.dao.VariableExpenseDao;
import com.alvarohdr.gastosapi.domain.dao.VariableExpenseTypeDao;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.model.VariableExpense;
import com.alvarohdr.gastosapi.domain.model.VariableExpenseType;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        VariableExpense variableExpense = variableExpenseDao.get(command.getId())
                .orElseThrow(() -> new RuntimeException("The variable expense with ID [" + command.getId() + "] doesn't exist or you are unauthorized"));
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
                User user = userService.getCurrentUser().orElseThrow(() -> new RuntimeException("Unauthorized access: Authentication is required to access this resource"));
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
