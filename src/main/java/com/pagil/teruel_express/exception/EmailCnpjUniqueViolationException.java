package com.pagil.teruel_express.exception;

public class EmailCnpjUniqueViolationException extends RuntimeException {
    public EmailCnpjUniqueViolationException(String message) {
        super(message);
    }
}
