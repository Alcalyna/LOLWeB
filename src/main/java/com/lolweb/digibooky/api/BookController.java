package com.lolweb.digibooky.api;

import com.lolweb.digibooky.service.BookService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.UpdateBookDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
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

    //PUT -> loan book, consumes a book and a user
    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanDto loanABook(@RequestBody UpdateBookDto updateBookDto) {
        bookService.updateBookAvailability(updateBookDto.getBookIsbn(), false);
        return bookService.loanBook(
                bookService
                        .getBookByIsbn(updateBookDto.getBookIsbn()),
                userService
                        .getUserRepository().getUserById(updateBookDto.getUserId()));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto registerBook(@RequestBody CreateBookDto newBook) {
        return bookService.addNewBook(newBook);

    }
}

