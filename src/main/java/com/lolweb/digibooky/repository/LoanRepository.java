package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.exceptions.BookNotInRepositoryException;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LoanRepository {

    private HashMap<UUID, BookLoan> activeLoans;

    public LoanRepository(HashMap<UUID, BookLoan> activeLoans) {
        this.activeLoans = activeLoans;
    }

    public HashMap<UUID, BookLoan> getActiveLoans() {
        return activeLoans;
    }

    public List<BookLoan> getAllLoans () {
        return activeLoans.values().stream().toList();
    }
}
