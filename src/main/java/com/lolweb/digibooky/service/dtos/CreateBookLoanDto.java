package com.lolweb.digibooky.service.dtos;

public class CreateBookLoanDto {

    private String isbn;
    private String userId;

    public String getIsbn() {
        return isbn;
    }

    public String getUserId() {
        return userId;
    }

    public CreateBookLoanDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
}
