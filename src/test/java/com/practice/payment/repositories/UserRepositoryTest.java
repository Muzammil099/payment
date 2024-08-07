package com.practice.payment.repositories;

import com.practice.payment.models.EndUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        EndUser user = new EndUser();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRoles(Set.of("USER"));
        userRepository.save(user);

        EndUser foundUser = userRepository.findByUsername("testuser").orElseThrow();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }
}
