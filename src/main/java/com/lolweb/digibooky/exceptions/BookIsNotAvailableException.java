package com.lolweb.digibooky.exceptions;

public class BookIsNotAvailableException extends RuntimeException{

    public BookIsNotAvailableException (String message){
        super(message);
    }
}
