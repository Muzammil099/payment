package com.practice.payment.services;

import com.practice.payment.models.Account;
import com.practice.payment.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> findById(long userId) {
        return accountRepository.findById(userId);
    }

    public void save(Account account){
        accountRepository.save(account);
    }
}
