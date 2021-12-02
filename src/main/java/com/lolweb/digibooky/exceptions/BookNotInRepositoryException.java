package com.lolweb.digibooky.exceptions;

public class BookNotInRepositoryException extends RuntimeException {

    public BookNotInRepositoryException() {
        super("Couldn't find the requested book in our library");
    }
}
