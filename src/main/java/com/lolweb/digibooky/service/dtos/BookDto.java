package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.author.Author;

import java.util.UUID;

public class BookDto {
    private UUID id;
    private String isbn;
    private String title;
    private Author author;
    private boolean isAvailable;

    public UUID getId() {
        return id;
    }

    public BookDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public BookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public BookDto setAvailable(boolean available) {
        this.isAvailable = available;
        return this;
    }
}
