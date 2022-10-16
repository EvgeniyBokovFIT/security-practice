package com.evgemba.securitypractice;

import com.evgemba.securitypractice.entity.Role;
import com.evgemba.securitypractice.entity.User;
import com.evgemba.securitypractice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityPracticeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Tom Danley", "Tommy", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Rose Curry", "Rose_", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Curt Johns", "superCurt", "123456", new ArrayList<>()));
            userService.saveUser(new User(null, "Mike Guidley", "Michelangelo", "123456", new ArrayList<>()));

            userService.addRoleToUser("Tommy", "ROLE_USER");
            userService.addRoleToUser("Rose_", "ROLE_ADMIN");
            userService.addRoleToUser("superCurt", "ROLE_MANAGER");
            userService.addRoleToUser("Michelangelo", "ROLE_SUPER_ADMIN");
        };
    }

}
