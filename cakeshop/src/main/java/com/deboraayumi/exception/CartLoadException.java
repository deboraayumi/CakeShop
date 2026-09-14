package com.deboraayumi.exception;

public class CartLoadException extends RuntimeException {
    
    public CartLoadException(String message){
        super(message);
    }
}
