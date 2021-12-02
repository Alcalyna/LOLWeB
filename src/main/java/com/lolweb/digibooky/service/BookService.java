package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookMapper bookMapper;
    private BookRepository bookRepository;
    private BookLoanService bookLoanService;

    public BookService(BookRepository bookRepository,BookLoanService bookLoanService) {
        this.bookMapper = new BookMapper();
        this.bookRepository = bookRepository;
        this.bookLoanService = bookLoanService;
    }

    public List<BookDto> getAllBooksInLibrary() {
        return bookRepository.getAll().stream().map(book -> bookMapper.mapToBookDto(book)).collect(Collectors.toList());
    }

    public BookDto getBookById(UUID id) {
        Book bookToReturn = BookRepository.getBookById(id);
        return bookMapper.mapToBookDto(bookToReturn);
    }

    public List<BookDto> getBookByTitle(String title){
        return BookRepository.getBookByTitle(title).stream()
                .map(book -> bookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookByAuthor(String authorName) {
        return BookRepository.getBookByAuthor(authorName).stream()
                .map(book -> bookMapper.mapToBookDto(book))
                .collect(Collectors.toList());
    }

    public Book getBookByIsbn(String isbn){
        return bookRepository
                .getAll()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn) && book.isAvailable())
                .collect(Collectors.toList())
                .get(0);
    }

    public BookDto addNewBook(CreateBookDto newBook) {
                        Book book = bookMapper.mapCreateBookDtoToBook(newBook);
                        BookRepository.booksInLibrary.put(book.getId(), book);
                        return bookMapper.mapToBookDto(book);
    }

}
