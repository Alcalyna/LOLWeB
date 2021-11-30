package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.loan.BookLoan;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class LoanRepository {

    private HashMap<UUID, BookLoan> activeLoans;

    public LoanRepository() {
        this.activeLoans = new HashMap<>();
    }

    public HashMap<UUID, BookLoan> getActiveLoans() {
        return activeLoans;
    }

/*
    --> only librarian + admin:
    public List<BookLoan> getAllLoans () {
        return activeLoans.values().stream().toList();
    }*/
}
