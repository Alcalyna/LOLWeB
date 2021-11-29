package com.lolweb.digibooky.domain;

import com.lolweb.digibooky.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookRepository {
    private HashMap<UUID, Book> booksInLibrary;

    public BookRepository() {
        this.booksInLibrary = new HashMap<>();
    }

    public Book save(Book book){
        booksInLibrary.put(book.getId(),book);
        return book;
    }

    public List<Book> getAll(){
        return booksInLibrary.values().stream().collect(Collectors.toList());
    }

    public Book getBookById(UUID id) {
        return booksInLibrary.get(id);
    }
}
