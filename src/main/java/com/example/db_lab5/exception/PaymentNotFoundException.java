package com.example.db_lab5.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Integer id){
        super("Could not find 'payment' with id=" + id);
    }
}
