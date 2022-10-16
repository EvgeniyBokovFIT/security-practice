package com.evgemba.securitypractice.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
