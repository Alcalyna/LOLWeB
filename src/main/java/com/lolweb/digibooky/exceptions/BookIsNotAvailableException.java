package com.lolweb.digibooky.exceptions;

public class BookIsNotAvailableException extends RuntimeException{

    public BookIsNotAvailableException (){
        super("The book you requested is currently not available for borrowing");
    }
}
