package com.lolweb.digibooky.domain.book;

import com.lolweb.digibooky.domain.author.Author;
import java.util.Objects;
import java.util.UUID;

public class Book {

    private final UUID id;
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private boolean isAvailable;

    public Book(BookBuilder builder) {
        this.id = builder.id;
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.summary = builder.summary;
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

    public String getSummary() {
        return summary;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public static final class BookBuilder {
        private UUID id;
        private String isbn;
        private String title;
        private Author author;
        private String summary;
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

        public BookBuilder withId(UUID id) {
            this.id = id;
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

        public BookBuilder withSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isAvailable == book.isAvailable && Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(summary, book.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, summary, isAvailable);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", isAvailable=" + isAvailable +
                '}';
    }
}