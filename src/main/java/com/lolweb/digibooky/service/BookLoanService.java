package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.exceptions.BookIsNotAvailableException;
import com.lolweb.digibooky.exceptions.BookNotInRepositoryException;
import com.lolweb.digibooky.exceptions.UserDoesNotExistException;
import com.lolweb.digibooky.repository.*;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookLoanDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import com.lolweb.digibooky.service.mappers.BookMapper;
import com.lolweb.digibooky.service.mappers.LoanMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookLoanService {

    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private SecurityService securityService;
    private UserService userService;

    public BookLoanService(LoanRepository loanRepository, BookRepository bookRepository, SecurityService securityService, UserService userService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.securityService = securityService;
        this.userService = userService;
    }

    public LocalDate calculateDueDate() {
        return LocalDate.now().plusWeeks(3);
    }

    public LoanRepository getLoanRepository() {
        return loanRepository;
    }

    public Book isAvailableToLoan(String isbn) {
        List<Book> books = bookRepository.getAllByIsbn(isbn);
        if(books.isEmpty()) {
            throw new BookNotInRepositoryException();
        }
        for(Book book: books) {
            if(book.isAvailable()) {
                return book;
            }
        }
        throw new BookIsNotAvailableException();
    }

    public BookLoanDto createBookLoan(CreateBookLoanDto createBookLoanDto, String authorization) {
        Book bookToLoan = isAvailableToLoan(createBookLoanDto.getIsbn());
        UUID memberId = securityService.getCurrentUser(authorization).getId();
        loanRepository.saveBookMemberMap(bookToLoan.getId(), memberId);

        BookLoan bookLoan = new BookLoan(calculateDueDate(), memberId, createBookLoanDto.getIsbn());
        loanRepository.save(bookLoan);
        bookToLoan.setAvailable(false);

        return LoanMapper.mapToLoanDto(bookLoan);
    }

    public List<BookDto> getLentBooksByMember(UUID idMember) {
        if(userService.getUserById(idMember) == null) {
            throw new UserDoesNotExistException();
        }
        return loanRepository.getAllLentBooksByMember(idMember).stream()
                .map(bookId -> BookMapper.mapToBookDto(bookRepository.getBookById(bookId)))
                .collect(Collectors.toList());
    }

    public void returnBookLoan(UUID idBookLoan, String authorization) {
        /// checking ...
        UUID memberId = securityService.getCurrentUser(authorization).getId();


    }
}
