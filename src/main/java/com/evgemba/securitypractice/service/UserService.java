package com.evgemba.securitypractice.service;

import com.evgemba.securitypractice.entity.Role;
import com.evgemba.securitypractice.entity.User;
import com.evgemba.securitypractice.exception.RoleNotFoundException;
import com.evgemba.securitypractice.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName) throws UserNotFoundException, RoleNotFoundException;

    User getUser(String username) throws UserNotFoundException;

    Page<User> getUsers(Pageable pageable);
}
