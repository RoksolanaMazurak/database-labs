package com.example.db_lab5.exception;

public class DriverHasNoCarException extends RuntimeException {
    public DriverHasNoCarException(Integer carId, Integer driverId){
        super("'driver' with id=" + driverId +  " doesn't have 'car' with id=" + carId);
    }
}
