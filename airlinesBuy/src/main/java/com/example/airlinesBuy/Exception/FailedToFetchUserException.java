package com.example.airlinesBuy.Exception;

public class FailedToFetchUserException extends RuntimeException {

    public FailedToFetchUserException() {
        super();
    }

    public FailedToFetchUserException(String message) {
        super(message);
    }
}
