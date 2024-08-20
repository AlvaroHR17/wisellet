package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.dao.UserDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("updateIncome")
public class UpdateIncomeController {
    private final IncomeDao incomeDao;
    private final IncomeTypeDao incomeTypeDao;
    private final UserDao userDao;

    @Autowired
    public UpdateIncomeController(IncomeDao incomeDao, IncomeTypeDao incomeTypeDao, UserDao userDao) {
        this.incomeDao = incomeDao;
        this.incomeTypeDao = incomeTypeDao;
        this.userDao = userDao;
    }

    @PutMapping
    public void update(@RequestBody UpdateTransactionCommand command) {
        Income income = incomeDao.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("The income with ID [" + command.getId() + "] doesn't exist"));
        Optional<IncomeType> optionalIncomeType = incomeTypeDao.findByDescription(command.getName());

        IncomeType incomeType, incomeTypeToDelete = null;
        List<Income> incomes = incomeDao.listIncomesByTypeDescription(income.getType().getDescription());

        if(optionalIncomeType.isPresent()) {
            incomeType = optionalIncomeType.get();
            if(incomes.size() <= 1) {
                incomeTypeToDelete = income.getType();
            }
        } else {
            if(incomes.size() > 1) {
                User user = userDao.findByUsername("Alvaro").orElseThrow(() -> new RuntimeException("El usuario no existe"));
                incomeType = new IncomeType();
                incomeType.setUser(user);
            } else {
                incomeType = income.getType();
            }
            incomeType.setDescription(command.getName());
            incomeTypeDao.saveOrUpdate(incomeType);
        }

        income.setType(incomeType);
        income.setAmount(command.getAmount());
        incomeDao.saveOrUpdate(income);

        if(Objects.nonNull(incomeTypeToDelete)) {
            incomeTypeDao.delete(incomeTypeToDelete);
        }
    }
}
