package com.pyramid.toupel.repository;


import com.pyramid.toupel.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Add query methods if needed, e.g.:
    // List<Transaction> findByType(String type);
}
