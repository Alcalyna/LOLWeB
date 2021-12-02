package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.service.dtos.CreateBookLoanDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;

import java.time.LocalDate;

public class LoanMapper {

    public static BookLoanDto mapToLoanDto (BookLoan loanToMap) {
        return new BookLoanDto()
                .setBorrowerId(loanToMap.getBorrowerId())
                .setBorrowedBookIsbn(loanToMap.getBorrowedBookIsbn())
                .setDueDate(loanToMap.getDueDate())
                .setId(loanToMap.getId());
    }

}
