package com.example.db_lab5.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Integer id){
        super("Could not find 'reservation' with id=" + id);
    }
}
