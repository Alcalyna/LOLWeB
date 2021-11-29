package com.lolweb.digibooky.domain.loan;

import java.time.LocalDate;
import java.util.UUID;

public class BookLoan {

    private final UUID id;
    private LocalDate dueDate;
    private final UUID borrowerId;
    private final String borrowedBookIsbn;

    public BookLoan(LocalDate dueDate, UUID borrowerId, String borrowedBookIsbn) {
        this.id = UUID.randomUUID();
        this.dueDate = dueDate;
        this.borrowerId = borrowerId;
        this.borrowedBookIsbn = borrowedBookIsbn;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public String getBorrowedBookIsbn() {
        return borrowedBookIsbn;
    }
}
