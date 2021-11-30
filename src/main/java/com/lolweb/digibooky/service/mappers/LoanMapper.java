package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;

public class LoanMapper {

    public BookLoanDto mapToLoanDto (BookLoan loanToMap) {
        return new BookLoanDto()
                .setBorrowerId(loanToMap.getBorrowerId())
                .setBorrowedBookIsbn(loanToMap.getBorrowedBookIsbn())
                .setDueDate(loanToMap.getDueDate())
                .setId(loanToMap.getId());
    }
}
