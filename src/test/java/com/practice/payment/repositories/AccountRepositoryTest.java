package com.practice.payment.repositories;

import com.practice.payment.models.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCreateAccount(){

        Account account = new Account();
        account.setUserId(1L);
        account.setBalance(BigDecimal.valueOf(1000));
        accountRepository.save(account);

        Optional<Account> foundAccount = accountRepository.findByUserId(1L);
        assertThat(foundAccount).isPresent();
        assertThat(foundAccount.get().getBalance()).isEqualByComparingTo(BigDecimal.valueOf(1000));


    }
}
