package com.example.db_lab5.exception;

public class CarShopNotFoundException extends RuntimeException {
    public CarShopNotFoundException(Integer id){
        super("Could not find 'car shop' with id=" + id);
    }
}
