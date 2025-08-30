package com.pagil.teruel_express.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameTypeException extends AuthenticationException {
    public UsernameTypeException(String message) {
        super(message);
    }
}
