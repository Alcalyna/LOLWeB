package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.repository.LoanRepository;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.BookLoanService;
import com.lolweb.digibooky.service.BookService;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest {

    private BookService bookService;
    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private BookLoanService bookLoanService;
    private BookMapper mapper;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserService userService;
    private SecurityService securityService;
    private BookController bookController;
    private Author author;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        loanRepository = new LoanRepository();
        securityService = new SecurityService(userRepository);
        bookLoanService = new BookLoanService(loanRepository, bookRepository, securityService);
        bookService = new BookService(bookRepository, bookLoanService);
        mapper = new BookMapper();
        userRepository = new UserRepository();
        userMapper = new UserMapper();
        userService = new UserService(userRepository, userMapper);
        securityService = new SecurityService(userRepository);
        bookController = new BookController(bookService, userService, securityService);
        author = new Author("Tim", "Le Massart");
        book1 = Book.BookBuilder.bookBuilder()
                .withAuthor(author)
                .withSummary("jdalfjfsflsf")
                .withIsbn("12122121212121")
                .withTitle("Life Of Tim")
                .withIsAvailable(true)
                .withId()
                .build();
        book2 = Book.BookBuilder.bookBuilder()
                .withAuthor(author)
                .withSummary("jdalfjsdsdsdsdfsflsf")
                .withIsbn("131331333131")
                .withTitle("La Life Of Tim")
                .withIsAvailable(true)
                .withId()
                .build();
        book3 = Book.BookBuilder.bookBuilder()
                .withAuthor(author)
                .withSummary("jdalfjfsflzezezzezzzzzsf")
                .withIsbn("138654313363")
                .withTitle("Le Life Of Tim")
                .withIsAvailable(true)
                .withId()
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    @Test
    void givenBookRepository_WhenSavingBooks_RepositoryNotEmpty () {
        //GIVEN
        //WHEN
        //THEN
        Assertions.assertFalse(bookRepository.getAll().isEmpty());

    }

    @Test
    void givenSeveralBooks_whenGetAllBooks_ReturnsAllBooks() {
        //GIVEN
        //WHEN

        List<BookDto> actual = bookController
                .getAllBooksInLibrary()
                .stream()
                .toList();

        List <BookDto> expected = List.of(mapper.mapToBookDto(book1), mapper.mapToBookDto(book2), mapper.mapToBookDto(book3));

        //THEN
        assertThat(actual).containsAll(expected);
    }

    @Test
    void givenABook_whenGetDetails_ReturnsFullDetailsOfBook () {
        //GIVEN
        //WHEN
        BookDto actual = bookController.getBookById(book1.getId());

        //THEN
        assertEquals(actual.getAuthor(), book1.getAuthor());
        assertEquals(actual.getIsbn(), book1.getIsbn());
        assertEquals(actual.getTitle(), book1.getTitle());
        assertEquals(actual.getSummary(), book1.getSummary());
        assertEquals(actual.isAvailable(), book1.isAvailable());
        assertEquals(actual.getId(), book1.getId());

    }
}
