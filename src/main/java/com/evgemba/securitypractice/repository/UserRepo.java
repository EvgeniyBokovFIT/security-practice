package com.evgemba.securitypractice.repository;

import com.evgemba.securitypractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
