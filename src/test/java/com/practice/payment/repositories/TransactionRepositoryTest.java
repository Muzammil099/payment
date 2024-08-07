package com.practice.payment.repositories;

import com.practice.payment.models.Account;
import com.practice.payment.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testCreateTransaction(){

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setSourceAccountId(1L);
        transaction.setDestinationAccountId(2L);
        transaction.setTimestamp(LocalDateTime.now());
        Transaction newEntry = transactionRepository.save(transaction);

        Optional<Transaction> foundAccount = transactionRepository.findById(newEntry.getId());
        assertThat(foundAccount).isPresent();
    }
}
