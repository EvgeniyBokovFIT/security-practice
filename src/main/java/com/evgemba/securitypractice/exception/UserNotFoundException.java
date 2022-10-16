package com.evgemba.securitypractice.exception;

/**
 * @author Evgeniy
 * @since 16.10.2022
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
