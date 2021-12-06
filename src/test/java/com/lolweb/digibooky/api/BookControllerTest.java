package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.exceptions.UserNotAuthorizedException;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.repository.LoanRepository;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.BookLoanService;
import com.lolweb.digibooky.service.BookService;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName(value = "main test class")
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

     @Nested
    @DisplayName(value = "SavingAndGettingBooksTest")
    class SavingAndGettingBooksTest {

        @BeforeEach
        void setUp() {
            initTesting();
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
        }

        @Test
        void givenBookRepository_WhenSavingBooks_RepositoryNotEmpty() {
            //GIVEN
            //WHEN
            //THEN
            assertFalse(bookRepository.getAll().isEmpty());
        }

        @Test
        void givenSeveralBooks_whenGetAllBooks_ReturnsAllBooks() {
            //GIVEN
            //WHEN

            List<BookDto> actual = bookController
                    .getAllBooksInLibrary()
                    .stream()
                    .toList();

            List<BookDto> expected = List.of(mapper.mapToBookDto(book1), mapper.mapToBookDto(book2), mapper.mapToBookDto(book3));

            //THEN
            assertThat(actual).containsAll(expected);
        }

        @Test
        void givenABook_whenGetDetails_ReturnsFullDetailsOfBook() {
            //GIVEN
            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);

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

        @Test
         void givenAnIsbn_ReturnsABook() {
            List<BookDto> result = bookController.getBookByIsbn("12122121212121");

            assertTrue(!result.isEmpty());
        }

        @Test
        void givenAPartialIsbn_ReturnsAListOfBooks() {
            List<BookDto> result = bookController.getBookByIsbn("1212212");

            assertTrue(!result.isEmpty());

        }

        @Test
         void givenABookTitle_ReturnsAListOfBooks() {
            List<BookDto> result = bookController.getBookByTitle("Le Life Of Tim");

            assertTrue(!result.isEmpty());
        }

        @Test
         void givenAPartialBookTitle_ReturnsAListOfBooks() {
            List<BookDto> result = bookController.getBookByTitle("Life Of");

            assertTrue(!result.isEmpty());
        }

        @Test
         void givenAnAuthor_ReturnsAListOfBooks() {
            List<BookDto> result = bookController.getBookByAuthor("Tim Le Massart");

            assertTrue(!result.isEmpty());
        }
    }

    @Nested
    @DisplayName(value = "RegisteringANewBookTest")
    class RegisteringANewBookTest {
        //an admin has all the possibilities of a librarian

        @BeforeEach
        void setUp() {
            initTesting();
        }

        @Test
        void givenLibrarian_WhenRegistersNewBook_RegisterBookIsAuthorized() {
            //GIVEN
            Author author = new Author("Lulinh", "Juniel");
            CreateBookDto createBookDto = new CreateBookDto()
                    .setIsbn("123456789000000")
                    .setAuthor(author)
                    .setTitle("LOLWeB")
                    .setSummary("hahahhahahahahahaha")
                    .setAvailable(true);

            CreateBookDto createBookDto2 = new CreateBookDto()
                    .setIsbn("123456789000000")
                    .setAuthor(author)
                    .setTitle("LOLWeB")
                    .setSummary("hahahhahahahahahaha")
                    .setAvailable(true);

            //WHEN
            bookController.registerBook(createBookDto, "Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu");
            BookDto actual = bookController.getAllBooksInLibrary().get(0);
            BookDto notIn = mapper.mapToBookDto(mapper.mapCreateBookDtoToBook(createBookDto2));

            //THEN
            assertThat(bookService.getAllBooksInLibrary()).contains(actual);

            //assert that the test is well written
            for (BookDto dto : bookService.getAllBooksInLibrary()) {
                assertNotEquals(dto, notIn);
            }
        }

        @Test
        void givenUserNotLibrarian_WhenRegistersNewBook_ReturnsIsNotAuthorized() {
            //GIVEN
            Author author = new Author("Lulinh", "Juniel");
            CreateBookDto createBookDto = new CreateBookDto()
                    .setIsbn("123456789000000")
                    .setAuthor(author)
                    .setTitle("LOLWeB")
                    .setSummary("hahahhahahahahahaha")
                    .setAvailable(true);

            //WHEN
            Throwable exception = catchThrowable(() -> bookController.registerBook(createBookDto, "Basic bWVtYmVyQGxvbHdlYi5jb206bWVtYmVy"));

            //THEN
            org.assertj.core.api.Assertions.assertThat(exception).isInstanceOf(UserNotAuthorizedException.class)
                    .hasMessage("You are not allowed to do this action.");
        }

        @Test
        void givenAdmin_WhenRegistersNewBook_RegisterBookIsAutorized() {
            //GIVEN
            Author author = new Author("Lulinh", "Juniel");
            CreateBookDto createBookDto = new CreateBookDto()
                    .setIsbn("123456789000000")
                    .setAuthor(author)
                    .setTitle("LOLWeB")
                    .setSummary("hahahhahahahahahaha")
                    .setAvailable(true);

            //WHEN
            bookController.registerBook(createBookDto, "Basic YWRtaW5AbG9sd2ViLmNvbTphZG1pbg==");
            BookDto actual = bookController.getAllBooksInLibrary().get(0);

            //THEN
            assertThat(bookService.getAllBooksInLibrary()).contains(actual);
        }
    }

    private void initTesting() {
        bookRepository = new BookRepository();
        loanRepository = new LoanRepository();
        securityService = new SecurityService(userRepository);
        bookLoanService = new BookLoanService(loanRepository, bookRepository, securityService, userService);
        bookService = new BookService(bookRepository, bookLoanService);
        mapper = new BookMapper();
        userRepository = new UserRepository();
        userMapper = new UserMapper();
        userService = new UserService(userRepository, securityService);
        securityService = new SecurityService(userRepository);
        bookController = new BookController(bookService, securityService);
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
    }
}
