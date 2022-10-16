package com.evgemba.securitypractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
@Getter
@Setter
@AllArgsConstructor
public class RoleToUserDTO {
    private String username;
    private String roleName;
}
