package com.pagil.teruel_express.exception;

public class CityStateUniqueViolationException extends RuntimeException {
    public CityStateUniqueViolationException(String message) {
        super(message);
    }
}
