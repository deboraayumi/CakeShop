package com.deboraayumi.exception;

public class InvalidProductArgumentException extends RuntimeException {
    
    public InvalidProductArgumentException(String message){
        super(message);
    }
}
