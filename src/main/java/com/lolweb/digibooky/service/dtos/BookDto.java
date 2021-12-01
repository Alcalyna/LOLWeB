package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.author.Author;

import java.util.Objects;
import java.util.UUID;

public class BookDto {
    private UUID id;
    private String isbn;
    private String title;
    private Author author;
    private boolean isAvailable;
    private String summary;

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


    public String getSummary() { return summary;
    }

    public BookDto setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public BookDto setAvailable(boolean available) {
        this.isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return isAvailable == bookDto.isAvailable && Objects.equals(id, bookDto.id) && Objects.equals(isbn, bookDto.isbn) && Objects.equals(title, bookDto.title) && Objects.equals(author, bookDto.author) && Objects.equals(summary, bookDto.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, isAvailable, summary);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", isAvailable=" + isAvailable +
                ", summary='" + summary + '\'' +
                '}';
    }
}
