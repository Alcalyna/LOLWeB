package com.lolweb.digibooky.api;

import com.lolweb.digibooky.service.BookService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooksInLibrary() {
        return bookService.getAllBooksInLibrary();
    }

    @GetMapping(produces = "application/json", path = "/id")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable("id") UUID id) {
        return bookService.getBookById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto registerBook(@RequestBody CreateBookDto newBook) {
        return bookService.addNewBook(newBook);
    }
}
