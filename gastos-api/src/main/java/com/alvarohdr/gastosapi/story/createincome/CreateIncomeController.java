package com.alvarohdr.gastosapi.story.createincome;

import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.dao.UserDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("createIncome")
public class CreateIncomeController {
    private final IncomeTypeDao incomeTypeDao;
    private final UserDao userDao;
    private final IncomeDao incomeDao;

    @Autowired
    public CreateIncomeController(IncomeTypeDao incomeTypeDao, UserDao userDao, IncomeDao incomeDao) {
        this.incomeTypeDao = incomeTypeDao;
        this.userDao = userDao;
        this.incomeDao = incomeDao;
    }

    @PostMapping
    public void create(@RequestBody CreateIncomeCommand command) {
        // TODO: Create createTransactionRouting which redirects to each type of transaction using a visitor
        Optional<IncomeType> optionalIncomeType = incomeTypeDao.findByDescription(command.getName());
        // find user session?
        User user = userDao.findByUsername("Alvaro").orElseThrow(() -> new RuntimeException("El usuario no existe"));
        IncomeType incomeType;
        if(optionalIncomeType.isPresent()) {
            incomeType = optionalIncomeType.get();
        } else {
            incomeType = new IncomeType();
            incomeType.setDescription(command.getName());
            incomeType.setUser(user);
            incomeTypeDao.saveOrUpdate(incomeType);
        }

        Assert.isTrue(!command.getName().isEmpty(), "The transaction type is required");
        Assert.isTrue((command.getAmount() > 0), "The amount is required");

        Income income = new Income();
        income.setUser(user);
        income.setCreationDate(LocalDate.now());
        income.setType(incomeType);
        income.setAmount(command.getAmount());
        incomeDao.saveOrUpdate(income);
    }
}
