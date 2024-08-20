package com.alvarohdr.gastosapi.story.deletetransaction;

import com.alvarohdr.gastosapi.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deleteTransaction")
public class DeleteTransactionController {
    private final TransactionService transactionService;

    @Autowired
    public DeleteTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        transactionService.findById(id).ifPresent(transactionService::deleteTransaction);
        // TODO: delete type if it's unused??
    }
}
