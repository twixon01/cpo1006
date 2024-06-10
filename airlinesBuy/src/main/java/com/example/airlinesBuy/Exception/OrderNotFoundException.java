package com.example.airlinesBuy.Exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
