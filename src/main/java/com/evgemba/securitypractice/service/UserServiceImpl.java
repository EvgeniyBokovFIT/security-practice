package com.evgemba.securitypractice.service;

import com.evgemba.securitypractice.entity.Role;
import com.evgemba.securitypractice.entity.User;
import com.evgemba.securitypractice.exception.RoleNotFoundException;
import com.evgemba.securitypractice.exception.UserNotFoundException;
import com.evgemba.securitypractice.repository.RoleRepo;
import com.evgemba.securitypractice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = getUser(username);
            log.info("User " + username + " found in the database");
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails
                    .User(user.getUsername(), user.getPassword(), authorities);
        } catch (UserNotFoundException e) {
            log.error("User " + username + " not found in the database");
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user " + user.getName() + " to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role " + role.getName() + " to the database");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws UserNotFoundException, RoleNotFoundException {
        log.info("Adding role " + roleName + " to user " + username);
        User user = userRepo.findByUsername(username).
                orElseThrow(() -> new UserNotFoundException("Username " + username + " not found"));
        Role role = roleRepo.findByName(roleName).
                orElseThrow(() -> new RoleNotFoundException("Role " + roleName + " not found"));

        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        log.info("Fetching user " + username);
        return userRepo.findByUsername(username).
                orElseThrow(() -> new UserNotFoundException("Username " + username + " not found"));
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        log.info("Fetching all users");
        return userRepo.findAll(pageable);
    }


}
