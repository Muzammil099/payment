package com.practice.payment.services;

import com.practice.payment.models.Account;
import com.practice.payment.models.EndUser;
import com.practice.payment.repositories.AccountRepository;
import com.practice.payment.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testTransferFundsSuccess() {
        EndUser user = new EndUser();
        user.setUsername("testuser");
        user.setPassword("password");
        user = userRepository.save(user);

        Account sourceAccount = new Account();
        sourceAccount.setUserId(user.getId());
        sourceAccount.setBalance(BigDecimal.valueOf(1000));
        sourceAccount = accountRepository.save(sourceAccount);

        Account targetAccount = new Account();
        targetAccount.setUserId(user.getId());
        targetAccount.setBalance(BigDecimal.valueOf(500));
        targetAccount = accountRepository.save(targetAccount);

        transactionService.transferFunds(sourceAccount.getId(), targetAccount.getId(), BigDecimal.valueOf(200));

        assertEquals(0, BigDecimal.valueOf(800).compareTo(accountRepository.findById(sourceAccount.getId()).get().getBalance()));
        assertEquals(0, BigDecimal.valueOf(700).compareTo(accountRepository.findById(targetAccount.getId()).get().getBalance()));
    }

    @Test
    public void testTransferFundsFailureInsufficientFunds() {
        EndUser user = new EndUser();
        user.setUsername("testuser");
        user.setPassword("password");
        user = userRepository.save(user);

        Account sourceAccount = new Account();
        sourceAccount.setUserId(user.getId());
        sourceAccount.setBalance(BigDecimal.valueOf(100));
        final Account createdSourceAccount = accountRepository.save(sourceAccount);

        Account targetAccount = new Account();
        targetAccount.setUserId(user.getId());
        targetAccount.setBalance(BigDecimal.valueOf(500));
        final Account createdtargetAccount = accountRepository.save(targetAccount);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferFunds(createdSourceAccount.getId(), createdtargetAccount.getId(), BigDecimal.valueOf(200));
        });

        assertEquals(0, BigDecimal.valueOf(100).compareTo(accountRepository.findById(sourceAccount.getId()).get().getBalance()));
        assertEquals(0, BigDecimal.valueOf(500).compareTo(accountRepository.findById(targetAccount.getId()).get().getBalance()));
    }
}
