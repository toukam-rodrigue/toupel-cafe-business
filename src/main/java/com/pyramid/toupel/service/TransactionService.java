package com.pyramid.toupel.service;

import com.pyramid.toupel.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final List<Transaction> store = new ArrayList<>();

    public List<Transaction> findAll() {
        return store;
    }

    public Transaction add(Transaction t) {
        t.setId((long) (store.size() + 1));
        store.add(t);
        return t;
    }

}
