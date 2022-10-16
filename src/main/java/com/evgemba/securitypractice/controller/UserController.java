package com.evgemba.securitypractice.controller;

import com.evgemba.securitypractice.dto.RoleToUserDTO;
import com.evgemba.securitypractice.entity.Role;
import com.evgemba.securitypractice.entity.User;
import com.evgemba.securitypractice.exception.RoleNotFoundException;
import com.evgemba.securitypractice.exception.UserNotFoundException;
import com.evgemba.securitypractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(@PageableDefault(sort = {"id"}, direction = DESC) Pageable pageable) {
        return ResponseEntity.ok().body(userService.getUsers(pageable));
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO request) {
        try {
            userService.addRoleToUser(request.getUsername(), request.getRoleName());
            return ResponseEntity.ok().body("Role " + request.getRoleName() + " successfully added to user " + request.getUsername());
        } catch (UserNotFoundException | RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
