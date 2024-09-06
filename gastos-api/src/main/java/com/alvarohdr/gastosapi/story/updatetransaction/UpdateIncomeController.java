package com.alvarohdr.gastosapi.story.updatetransaction;

import com.alvarohdr.gastosapi.domain.dao.IncomeDao;
import com.alvarohdr.gastosapi.domain.dao.IncomeTypeDao;
import com.alvarohdr.gastosapi.domain.model.Income;
import com.alvarohdr.gastosapi.domain.model.IncomeType;
import com.alvarohdr.gastosapi.domain.model.User;
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
@RequestMapping("/v2/updateIncome")
public class UpdateIncomeController {
    private final IncomeDao incomeDao;
    private final IncomeTypeDao incomeTypeDao;
    private final UserService userService;

    @Autowired
    public UpdateIncomeController(IncomeDao incomeDao, IncomeTypeDao incomeTypeDao, UserService userService) {
        this.incomeDao = incomeDao;
        this.incomeTypeDao = incomeTypeDao;
        this.userService = userService;
    }

    @PutMapping
    public void update(@RequestBody UpdateTransactionCommand command) {

        Income income = incomeDao.get(command.getId())
                .orElseThrow(() -> new RuntimeException("The income with ID [" + command.getId() + "] doesn't exist or you are unauthorized"));
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
                User user = userService.getCurrentUser().orElseThrow(() -> new RuntimeException("Unauthorized access: Authentication is required to access this resource"));
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
