package com.pyramid.toupel.controller;


import com.pyramid.toupel.model.Transaction;
import com.pyramid.toupel.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> all() {
        return service.findAll();
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.add(transaction);
    }

}
