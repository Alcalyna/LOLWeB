package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.loan.BookLoan;
import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.repository.LoanRepository;
import com.lolweb.digibooky.service.BookLoanService;
import com.lolweb.digibooky.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookloans")
public class BookLoanController {

    private BookLoanService bookLoanService;

    public BookLoanController() {
        this.bookLoanService = new BookLoanService(new LoanRepository(),new BookRepository());
    }
}
