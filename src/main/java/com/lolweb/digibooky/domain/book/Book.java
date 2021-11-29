package com.lolweb.digibooky.domain.book;

import com.lolweb.digibooky.domain.author.Author;

import java.util.UUID;

public class Book {

    private final UUID id;
    private final String isbn;
    private final String title;
    private final Author author;
    private boolean isAvailable;

    public Book(String isbn, String title, Author author, boolean isAvailable) {
        this.id = UUID.randomUUID();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public UUID getId() {
        return id;
    }

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
}