package com.pyramid.toupel.service;


import com.pyramid.toupel.model.Transaction;
import com.pyramid.toupel.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {


    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction add(Transaction t) {
        return repository.save(t);
    }

}
