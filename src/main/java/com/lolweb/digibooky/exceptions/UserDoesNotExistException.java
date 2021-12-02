package com.lolweb.digibooky.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("This user does not exist");
    }
}
