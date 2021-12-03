package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.service.BookService;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private UserService userService;
    private final SecurityService securityService;

    public BookController(BookService bookService, UserService userService, SecurityService securityService) {
        this.bookService = bookService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooksInLibrary() {
        return bookService.getAllBooksInLibrary();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable("id") UUID id) {
        return bookService.getBookDtoById(id);
    }

    @GetMapping(params = "title")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByTitle(@RequestParam String title) {
        return bookService.getBookDtoByTitle(title);
    }

    @GetMapping(params = "authorName")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByAuthor(@RequestParam String authorName) {
        return bookService.getBookByAuthor(authorName);
    }

    @GetMapping(params = "isbn")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByIsbn(@RequestParam String isbn) {
        return bookService.getBookDtoByIsbn(isbn);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto registerBook(@RequestBody CreateBookDto newBook, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.REGISTER_BOOK);
        return bookService.addNewBook(newBook);
    }
}

