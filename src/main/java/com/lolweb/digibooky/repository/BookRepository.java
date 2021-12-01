package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookRepository {
    public final static HashMap<UUID, Book> booksInLibrary = new HashMap<>();

    public BookRepository() {
    }

    public Book save(Book book){
        booksInLibrary.put(book.getId(),book);
        return book;
    }

    public List<Book> getAll(){
        return booksInLibrary.values().stream().collect(Collectors.toList());
    }

    public static Book getBookById(UUID id) {
        return booksInLibrary.get(id);
    }

    public static void initBooks() {
        Book newBook = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Dalinh", "Luniel"))
                .withIsAvailable(true)
                .withIsbn("1234567894564")
                .withTitle("LOLWeB is the best group")
                .build();
        booksInLibrary.put(newBook.getId(), newBook);
    }
}
