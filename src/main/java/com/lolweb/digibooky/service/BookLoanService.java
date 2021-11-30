package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.exceptions.*;
import com.lolweb.digibooky.repository.*;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import com.lolweb.digibooky.service.mappers.LoanMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookLoanService {

    private LoanRepository loanRepository;
    private BookRepository bookRepository;
    private LoanMapper loanMapper;

    public BookLoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.loanMapper = new LoanMapper();
    }

    public BookLoan createLoan (Book toLoan, User borrower) throws BookNotInRepositoryException, BookIsNotAvailableException {
        if (!(bookRepository.getAll().contains(toLoan)))
            throw new BookNotInRepositoryException("Couldn't find the requested book in our library");
        if (!(bookRepository.getBookById(toLoan.getId()).isAvailable()))
            throw new BookIsNotAvailableException("The book you requested is currently not available for borrowing");
        return new BookLoan(calculateDueDate(), borrower.getId(), toLoan.getIsbn());
    }

    public LocalDate calculateDueDate() {
        return LocalDate.now().plusWeeks(3);
    }

    public LoanRepository getLoanRepository() {
        return loanRepository;
    }

    public LoanMapper getLoanMapper() {
        return loanMapper;
    }
}
