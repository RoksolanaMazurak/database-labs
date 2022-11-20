package com.example.db_lab5.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Integer id){
        super("Could not find 'car' with id=" + id);
    }
}
