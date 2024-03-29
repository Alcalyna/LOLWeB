package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookLoanService bookLoanService;

    public BookService(BookRepository bookRepository, BookLoanService bookLoanService) {
        this.bookRepository = bookRepository;
        this.bookLoanService = bookLoanService;
    }

    public List<BookDto> getAllBooksInLibrary() {
        return bookRepository.getAll().stream()
                .map(book -> BookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public Book getBookById(UUID id) {
        return this.bookRepository.getById(id);
    }

    public BookDto getBookDtoById(UUID id) {
        Book bookToReturn = bookRepository.getById(id);
        return BookMapper.mapToBookDto(bookToReturn);
    }

    public List<Book> getBookByTitle(String title) {
        title = title.toLowerCase();
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepository.getAll()) {
            String titleBook = book.getTitle().toLowerCase();
            if (titleBook.contains(title)) {
                books.add(book);
            }
        }
        return books;
    }

    private List<Book> getBookByIsbn(String isbn) {
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepository.getAll()) {
            String bookIsbn = book.getIsbn();
            if (bookIsbn.contains(isbn)) {
                books.add(book);
            }
        }
        return books;
    }

    public List<BookDto> getBookDtoByTitle(String title) {
        return this.getBookByTitle(title).stream()
                    .map(book -> BookMapper.mapToBookDto(book))
                    .collect(Collectors.toList());
    }

    public List<BookDto> getBookByAuthor(String authorName) {
        return bookRepository.getBookByAuthor(authorName).stream()
                .map(book -> BookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookDtoByIsbn(String isbn) {
        return this.getBookByIsbn(isbn).stream()
                .map(book -> BookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public BookDto addNewBook(CreateBookDto newBook) {
        Book book = BookMapper.mapCreateBookDtoToBook(newBook);
        if(bookValidInputs(book)) {
            book.setAvailable(true);
            bookRepository.getBooksInLibrary().put(book.getId(), book);
        }
        return BookMapper.mapToBookDto(book);
    }

    public boolean bookValidInputs(Book book) {
        String isbn = book.getIsbn();
        String authorLastName = book.getAuthor().getLastName();
        String title = book.getTitle();
        if(isbn == null || isbn.trim().equals("")) {
            throw new IllegalArgumentException("The ISBN should be filled!");
        }
        if(authorLastName == null || authorLastName.trim().equals("")) {
            throw new IllegalArgumentException("The author's last name should be filled!");
        }
        if(title == null || title.trim().equals("")) {
            throw new IllegalArgumentException("The title should be filled!");
        }
        return true;
    }
}
