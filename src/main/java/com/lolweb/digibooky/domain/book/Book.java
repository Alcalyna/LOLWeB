package com.lolweb.digibooky.domain.book;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.lolweb.digibooky.domain.author.Author;

import java.util.UUID;

public class Book {

    private final UUID id;
    private final String isbn;
    private final String title;
    private final Author author;
    private boolean isAvailable;

    public Book(BookBuilder builder) {
        this.id = builder.id;
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.isAvailable = builder.isAvailable;
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


    public static final class BookBuilder {
        private UUID id;
        private String isbn;
        private String title;
        private Author author;
        private boolean isAvailable;

        private BookBuilder() {
        }

        public static BookBuilder bookBuilder() {
            return new BookBuilder();
        }

        public BookBuilder withId() {
            this.id = UUID.randomUUID();
            return this;
        }

        public BookBuilder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder withAuthor(Author author) {
            this.author = author;
            return this;
        }

        public BookBuilder withIsAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}