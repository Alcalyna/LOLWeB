package com.lolweb.digibooky.service.dtos.loandto;

import java.time.LocalDate;
import java.util.UUID;

public class BookLoanDto {

    private UUID id;
    private LocalDate dueDate;
    private UUID borrowerId;
    private String borrowedBookIsbn;

    public UUID getId() {
        return id;
    }

    public BookLoanDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookLoanDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public BookLoanDto setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
        return this;
    }

    public String getBorrowedBookIsbn() {
        return borrowedBookIsbn;
    }

    public BookLoanDto setBorrowedBookIsbn(String borrowedBookIsbn) {
        this.borrowedBookIsbn = borrowedBookIsbn;
        return this;
    }
}

