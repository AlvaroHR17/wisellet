package com.alvarohdr.gastosapi.story;

import com.alvarohdr.gastosapi.domain.dao.TransactionDao;
import com.alvarohdr.gastosapi.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EventController {

    private final TransactionDao transactionDao;

    @Autowired
    public EventController(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping
    public String getAll(){
        List<Transaction> all = transactionDao.findAll();
        return "HOLA MUNDO";
    }
}
