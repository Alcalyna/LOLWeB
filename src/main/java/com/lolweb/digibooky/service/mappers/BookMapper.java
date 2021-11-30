package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import org.springframework.stereotype.Component;

import static com.lolweb.digibooky.domain.book.Book.*;

@Component
public class BookMapper {

    public BookDto mapToBookDto(Book book) {
        return new BookDto()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setAvailable(book.isAvailable());
    }

    public Book mapDtoToBook(BookDto bookDto) {
        return new Book(bookDto);
    }

    public Book mapCreateBookDtoToBook(CreateBookDto createBookDto) {
        return new Book(createBookDto);
    }

    public Book mapToBook(CreateBookDto createBookDto) {
        return BookBuilder
                .bookBuilder()
                .withAuthor(createBookDto.getAuthor())
                .withIsbn(createBookDto.getIsbn())
                .withTitle(createBookDto.getTitle())
                .withIsAvailable(createBookDto.isAvailable())
                .build();
    }
}
