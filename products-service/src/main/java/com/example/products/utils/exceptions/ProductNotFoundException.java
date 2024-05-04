package com.example.products.utils.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(){

    }
    public ProductNotFoundException(String message){super(message);}
    public ProductNotFoundException(Throwable cause){super(cause);}
    public ProductNotFoundException(String message, Throwable cause){super(message,cause);}
}
