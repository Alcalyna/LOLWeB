package com.lolweb.digibooky.service.dtos;

public class CreateBookLoanDto {

    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public CreateBookLoanDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
}
