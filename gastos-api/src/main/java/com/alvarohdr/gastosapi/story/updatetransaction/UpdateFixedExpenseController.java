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
@RequestMapping("/v2/updateFixedExpense")
public class UpdateFixedExpenseController {
    private final FixedExpenseDao fixedExpenseDao;
    private final FixedExpenseTypeDao fixedExpenseTypeDao;
    private final UserService userService;

    @Autowired
    public UpdateFixedExpenseController(FixedExpenseDao fixedExpenseDao, FixedExpenseTypeDao fixedExpenseTypeDao, UserService userService) {
        this.fixedExpenseDao = fixedExpenseDao;
        this.fixedExpenseTypeDao = fixedExpenseTypeDao;
        this.userService = userService;
    }

    @PutMapping
    public void update(@RequestBody UpdateTransactionCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());

        FixedExpense fixedExpense = fixedExpenseDao.getSecure(command.getId(), userId)
                .orElseThrow(() -> new RuntimeException("The fixed expense with ID [" + command.getId() + "] doesn't exist for the user [" + userId + "]"));
        Optional<FixedExpenseType> optionalFixedExpenseType = fixedExpenseTypeDao.findByDescription(command.getName(), userId);

        FixedExpenseType fixedExpenseType, fixedExpenseTypeToDelete = null;
        List<FixedExpense> fixedExpenses = fixedExpenseDao.listFixedExpensesByTypeDescription(fixedExpense.getType().getDescription());

        if(optionalFixedExpenseType.isPresent()) {
            fixedExpenseType = optionalFixedExpenseType.get();
            if(fixedExpenses.size() <= 1) {
                fixedExpenseTypeToDelete = fixedExpense.getType();
            }
        } else {
            if(fixedExpenses.size() > 1) {
                User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("The user with id [" + userId + "] doesn´t exist"));
                fixedExpenseType = new FixedExpenseType();
                fixedExpenseType.setUser(user);
            } else {
                fixedExpenseType = fixedExpense.getType();
            }
            fixedExpenseType.setDescription(command.getName());
            fixedExpenseTypeDao.saveOrUpdate(fixedExpenseType);
        }

        fixedExpense.setType(fixedExpenseType);
        fixedExpense.setAmount(command.getAmount());
        fixedExpenseDao.saveOrUpdate(fixedExpense);

        if(Objects.nonNull(fixedExpenseTypeToDelete)) {
            fixedExpenseTypeDao.delete(fixedExpenseTypeToDelete);
        }
    }
}
