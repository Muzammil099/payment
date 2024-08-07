package com.practice.payment.repositories;

import com.practice.payment.models.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EndUser, Long> {
    Optional<EndUser> findByUsername(String username);
}
