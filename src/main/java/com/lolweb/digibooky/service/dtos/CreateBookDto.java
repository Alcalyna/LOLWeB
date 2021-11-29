package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.author.Author;

public class CreateBookDto {
    private String isbn;
    private String title;
    private Author author;
    private boolean isAvailable;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public CreateBookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public CreateBookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public CreateBookDto setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }
}
