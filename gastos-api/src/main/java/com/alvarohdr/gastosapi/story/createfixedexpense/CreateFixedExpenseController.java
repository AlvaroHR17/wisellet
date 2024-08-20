package com.alvarohdr.gastosapi.story.createfixedexpense;

import com.alvarohdr.gastosapi.domain.dao.*;
import com.alvarohdr.gastosapi.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("createFixedExpense")
public class CreateFixedExpenseController {
    private final FixedExpenseTypeDao fixedExpenseTypeDao;
    private final UserDao userDao;
    private final FixedExpenseDao fixedExpenseDao;

    @Autowired
    public CreateFixedExpenseController(FixedExpenseTypeDao fixedExpenseTypeDao, UserDao userDao, FixedExpenseDao fixedExpenseDao) {
        this.fixedExpenseTypeDao = fixedExpenseTypeDao;
        this.userDao = userDao;
        this.fixedExpenseDao = fixedExpenseDao;
    }

    @PostMapping
    public void create(@RequestBody CreateFixedExpenseCommand command) {
        Optional<FixedExpenseType> optionalFixedExpenseType = fixedExpenseTypeDao.findByDescription(command.getName());
        // find user session?
        User user = userDao.findByUsername("Alvaro").orElseThrow(() -> new RuntimeException("El usuario no existe"));
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
