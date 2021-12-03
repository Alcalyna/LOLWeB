package com.lolweb.digibooky.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(PasswordNotValidException.class)
    public void passwordNotValidException(PasswordNotValidException passwordNotValidException, HttpServletResponse response) throws  IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, passwordNotValidException.getMessage());
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public void userDoesNotExistException(UserDoesNotExistException userDoesNotExistException, HttpServletResponse response) throws  IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, userDoesNotExistException.getMessage());
    }

    @ExceptionHandler(BookNotInRepositoryException.class)
    public void setBookNotInRepositoryException(BookNotInRepositoryException bookNotInRepositoryException, HttpServletResponse response) throws  IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, bookNotInRepositoryException.getMessage());
    }
}
