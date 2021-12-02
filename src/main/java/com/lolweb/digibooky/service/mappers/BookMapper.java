package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import org.springframework.stereotype.Component;

import static com.lolweb.digibooky.domain.book.Book.*;

@Component
public class BookMapper {

    public static BookDto mapToBookDto(Book book) {
        return new BookDto()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary())
                .setAvailable(book.isAvailable());
    }


    public static Book mapCreateBookDtoToBook(CreateBookDto createBookDto) {
        return BookBuilder
                .bookBuilder()
                .withId()
                .withAuthor(createBookDto.getAuthor())
                .withSummary(createBookDto.getSummary())
                .withIsbn(createBookDto.getIsbn())
                .withTitle(createBookDto.getTitle())
                .withIsAvailable(createBookDto.isAvailable())
                .build();
    }
}
