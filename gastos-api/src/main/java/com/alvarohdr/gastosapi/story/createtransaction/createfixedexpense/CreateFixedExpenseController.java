package com.alvarohdr.gastosapi.story.createtransaction.createfixedexpense;

import com.alvarohdr.gastosapi.domain.dao.*;
import com.alvarohdr.gastosapi.domain.model.*;
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
@RequestMapping("/v2/createFixedExpense")
public class CreateFixedExpenseController {
    private final FixedExpenseTypeDao fixedExpenseTypeDao;
    private final UserService userService;
    private final FixedExpenseDao fixedExpenseDao;

    @Autowired
    public CreateFixedExpenseController(FixedExpenseTypeDao fixedExpenseTypeDao, UserService userService, FixedExpenseDao fixedExpenseDao) {
        this.fixedExpenseTypeDao = fixedExpenseTypeDao;
        this.userService = userService;
        this.fixedExpenseDao = fixedExpenseDao;
    }

    @PostMapping
    public void create(@RequestBody CreateFixedExpenseCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("The user with id {" + userId + "} doesn´t exist"));

        Optional<FixedExpenseType> optionalFixedExpenseType = fixedExpenseTypeDao.findByDescription(command.getName(), userId);

        FixedExpenseType fixedExpenseType;
        if(optionalFixedExpenseType.isPresent()) {
            fixedExpenseType = optionalFixedExpenseType.get();
        } else {
            fixedExpenseType = new FixedExpenseType();
            fixedExpenseType.setDescription(command.getName());
            fixedExpenseType.setUser(user);
            fixedExpenseTypeDao.saveOrUpdate(fixedExpenseType);
        }

        FixedExpense fixedExpense = new FixedExpense();
        fixedExpense.setUser(user);
        fixedExpense.setCreationDate(LocalDate.now());
        fixedExpense.setType(fixedExpenseType);
        fixedExpense.setAmount(command.getAmount());
        fixedExpenseDao.saveOrUpdate(fixedExpense);
    }
}
