package com.practice.payment.services;

import com.practice.payment.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class TransactionService {
    @Autowired
    private AccountService accountService;

    @Transactional
    public void transferFunds(long sourceAccountId, long targetAccountId, BigDecimal amount) {

        Account sourceAccount = accountService.findById(sourceAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid source account ID"));

        Account targetAccount = accountService.findById(targetAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid target account ID"));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));

        accountService.save(sourceAccount);
        accountService.save(targetAccount);
    }
}
