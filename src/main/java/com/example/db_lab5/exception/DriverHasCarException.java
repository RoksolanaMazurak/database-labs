package com.example.db_lab5.exception;

public class DriverHasCarException extends RuntimeException {
    public DriverHasCarException(Integer carId, Integer driverId){
        super("'driver' with id=" + driverId +  " already have 'car' with id=" + carId);
    }
}
