package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.exceptions.BookIsNotAvailableException;
import com.lolweb.digibooky.exceptions.BookNotInRepositoryException;
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
    private LoanMapper loanMapper;
    private SecurityService securityService;

    public BookLoanService(LoanRepository loanRepository, BookRepository bookRepository, SecurityService securityService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.loanMapper = new LoanMapper();
        this.securityService = securityService;
    }

//    public BookLoan createLoan (Book toLoan, User borrower) throws BookNotInRepositoryException, BookIsNotAvailableException {
//        if (!(bookRepository.getAll().contains(toLoan)))
//            throw new BookNotInRepositoryException("Couldn't find the requested book in our library");
//        if (!(bookRepository.getBookById(toLoan.getId()).isAvailable()))
//            throw new BookIsNotAvailableException("The book you requested is currently not available for borrowing");
//        return new BookLoan(calculateDueDate(), borrower.getId(), toLoan.getIsbn());
//    }

    public LocalDate calculateDueDate() {
        return LocalDate.now().plusWeeks(3);
    }

    public LoanRepository getLoanRepository() {
        return loanRepository;
    }

    public LoanMapper getLoanMapper() {
        return loanMapper;
    }

    public Book isAvailableToLoan(String isbn) {
        List<Book> books = BookRepository.getAllByIsbn(isbn);
        if(books.isEmpty()) {
            throw new BookNotInRepositoryException("Couldn't find the requested book in our library");
        }
        for(Book book: books) {
            if(book.isAvailable()) {
                return book;
            }
        }
        throw new BookIsNotAvailableException("The book you requested is currently not available for borrowing");
    }

    public BookLoanDto createBookLoan(CreateBookLoanDto createBookLoanDto, String authorization) {
        Book bookToLoan = isAvailableToLoan(createBookLoanDto.getIsbn());
        UUID memberId = securityService.getCurrentUser(authorization).getId();

        LoanRepository.saveBookMemberMap(bookToLoan.getId(), memberId);

        BookLoan bookLoan = new BookLoan(calculateDueDate(), memberId, createBookLoanDto.getIsbn());
        LoanRepository.save(bookLoan);
        bookToLoan.setAvailable(false);

        return LoanMapper.mapToLoanDto(bookLoan);

    }

    public List<BookDto> getLentBooksByMember(UUID idMember) {
        return LoanRepository.getAllLentBooksByMember(idMember).stream()
                .map(bookId -> BookMapper.mapToBookDto(BookRepository.getBookById(bookId)))
                .collect(Collectors.toList());
    }
}
