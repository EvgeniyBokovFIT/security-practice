package com.evgemba.securitypractice.exception;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
