package com.lolweb.digibooky.exceptions;

public class BookNotInRepositoryException extends RuntimeException {

    public BookNotInRepositoryException(String message) {
        super(message);
    }
}
