package com.pagil.teruel_express.exception;

public class EmailCpfUniqueViolationException extends RuntimeException {
    public EmailCpfUniqueViolationException(String message) {
        super(message);
    }
}
