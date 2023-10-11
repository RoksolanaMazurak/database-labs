package com.example.db_lab5.exception;

public class CarModelNotFoundException extends RuntimeException {
    public CarModelNotFoundException(Integer id){
        super("Could not find 'car model' with id=" + id);
    }
}
