package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.repository.LoanRepository;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.BookLoanService;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookLoanDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import com.lolweb.digibooky.service.mappers.LoanMapper;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookLoanControllerTest {

    private BookLoanController bookLoanController;
    private LoanRepository bookLoanRepository;
    private BookLoanService bookLoanService;
    private BookRepository bookRepository;
    private SecurityService securityService;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        securityService = new SecurityService(userRepository);
        userService = new UserService(userRepository, securityService);
        bookLoanRepository = new LoanRepository();
        bookRepository = new BookRepository();
        bookLoanService = new BookLoanService(bookLoanRepository, bookRepository, securityService, userService);
        bookLoanController = new BookLoanController(bookLoanService, securityService);

        bookRepository.initBooks();
        userRepository.initUsers();
    }

    @Test
    void givenBook_assertBookCanBeBorrowed() {
        //GIVEN
        Book book = bookRepository.getAll().get(0);
        CreateBookLoanDto createBookLoanDto = new CreateBookLoanDto().setIsbn(book.getIsbn());

        //WHEN
        UUID actual = bookLoanController.loanABook(createBookLoanDto,"Basic dGltQGxvbHdlYi5jb206dGlt").getId();

        //THEN
        assertTrue(bookLoanRepository.getMapOfLoans().containsKey(actual));
    }

    @Test
    void givenBook_whenBookIsReturned_thenLoanIsFinished() {
        //GIVEN
        Book book = bookRepository.getAll().get(0);
        CreateBookLoanDto createBookLoanDto = new CreateBookLoanDto().setIsbn(book.getIsbn());

        //WHEN
        UUID actual = bookLoanController.loanABook(createBookLoanDto,"Basic dGltQGxvbHdlYi5jb206dGlt").getId();
        bookLoanController.returnBook(actual,"Basic dGltQGxvbHdlYi5jb206dGlt" );
        //THEN
        assert(!bookLoanRepository.getMapOfLoans().containsKey(actual));
    }

    @Test
    void givenAMember_whenLibrarianGetAllLoansByMemberId_thenReturnAllBooksLentToMember() {
        //Given
        User member = userRepository.getUserByEmail("member@lolweb.com");
        Book book = bookRepository.getAll().get(0);
        String isbn = book.getIsbn();
        CreateBookLoanDto createBookLoanDto = new CreateBookLoanDto().setIsbn(isbn);
        bookLoanController.loanABook(createBookLoanDto,"Basic dGltQGxvbHdlYi5jb206dGlt");
        UUID bookId = book.getId();

        //When
        List<BookDto> actual =  bookLoanController.lentBooks(member.getId(),"Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu");

        //Then
        assertTrue(bookLoanRepository.getMapOfBooksPerMember().containsKey(bookId));
    }
}
