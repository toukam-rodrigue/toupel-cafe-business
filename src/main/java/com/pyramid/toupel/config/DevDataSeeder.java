package com.pyramid.toupel.config;


import com.pyramid.toupel.model.Transaction;
import com.pyramid.toupel.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Configuration
@Profile("dev")
public class DevDataSeeder {

    @Bean
    CommandLineRunner seedTransactions(TransactionRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return; // idempotent: do nothing if data already present
            }

            List<Transaction> seed = List.of(
                    Transaction.builder().type("PAYMENT").amount(new BigDecimal("19.99")).createdAt(OffsetDateTime.now()).build(),
                    Transaction.builder().type("REFUND").amount(new BigDecimal("5.50")).createdAt(OffsetDateTime.now()).build(),
                    Transaction.builder().type("TRANSFER").amount(new BigDecimal("250.00")).createdAt(OffsetDateTime.now()).build(),
                    Transaction.builder().type("PAYMENT").amount(new BigDecimal("99.90")).createdAt(OffsetDateTime.now()).build()
            );

            repository.saveAll(seed);
            System.out.println("[DevDataSeeder] Inserted " + seed.size() + " transactions.");
        };
    }
}
