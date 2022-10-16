package com.evgemba.securitypractice.repository;

import com.evgemba.securitypractice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
