package com.alvarohdr.gastosapi.story.createtransaction.createincome;

import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;
import com.alvarohdr.gastosapi.domain.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v2/createIncome")
public class CreateIncomeController {
    private final IncomeTypeDao incomeTypeDao;
    private final UserService userService;
    private final IncomeDao incomeDao;

    @Autowired
    public CreateIncomeController(IncomeTypeDao incomeTypeDao, UserService userService, IncomeDao incomeDao) {
        this.incomeTypeDao = incomeTypeDao;
        this.userService = userService;
        this.incomeDao = incomeDao;
    }

    @PostMapping
    public void create(@RequestBody CreateIncomeCommand command) {
        // TODO: Create createTransactionRouting which redirects to each type of transaction using a visitor
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = Long.parseLong(authentication.getPrincipal().toString());
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("The user with id {" + userId + "} doesn´t exist"));

        Optional<IncomeType> optionalIncomeType = incomeTypeDao.findByDescription(command.getName(), userId);

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
