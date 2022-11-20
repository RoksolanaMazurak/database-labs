package com.example.db_lab5.exception;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(Integer id){
        super("Could not find 'rating' with id=" + id);
    }
}
