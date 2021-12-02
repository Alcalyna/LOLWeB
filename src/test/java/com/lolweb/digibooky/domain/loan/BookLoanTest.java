package com.lolweb.digibooky.domain.loan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookLoanTest {
    BookLoan bookLoan;

    @BeforeEach
    public void init() {
        bookLoan = new BookLoan(LocalDate.now(), UUID.randomUUID(), "12345678900000");
    }

    @Test
    void getId() {
        Assertions.assertEquals(UUID.class, bookLoan.getId().getClass());
    }

    @Test
    void getDueDate() {
        Assertions.assertEquals(LocalDate.now(), bookLoan.getDueDate());
    }

    @Test
    void getBorrowerId() {
        Assertions.assertEquals(UUID.class, bookLoan.getBorrowerId().getClass());
    }

    @Test
    void getBorrowedBookIsbn() {
        Assertions.assertEquals("12345678900000", bookLoan.getBorrowedBookIsbn());
    }
}