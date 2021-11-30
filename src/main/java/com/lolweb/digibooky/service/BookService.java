package com.lolweb.digibooky.service;

import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.service.dtos.BookDto;
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

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooksInLibrary() {
        return bookRepository.getAll().stream().map(book -> bookMapper.mapToBookDto(book)).collect(Collectors.toList());
    }

    public BookDto getBookById(UUID id) {
        Book bookToReturn = bookRepository.getBookById(id);
        return bookMapper.mapToBookDto(bookToReturn);
    }

    public BookDto addNewBook(CreateBookDto newBook) {
        Book book = bookMapper.mapCreateBookDtoToBook(newBook);
        return bookMapper.mapToBookDto(bookRepository.save(book));
    }


}
