package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.service.BookLoanService;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.dtos.BookDto;
import com.lolweb.digibooky.service.dtos.CreateBookLoanDto;
import com.lolweb.digibooky.service.dtos.loandto.BookLoanDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookloans")
public class BookLoanController {

    private BookLoanService bookLoanService;
    private SecurityService securityService;

    public BookLoanController(BookLoanService bookLoanService, SecurityService securityService) {
        this.bookLoanService = bookLoanService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BookLoanDto loanABook(@RequestBody CreateBookLoanDto createBookLoanDto, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.BORROW_BOOK);
        return bookLoanService.createBookLoan(createBookLoanDto, authorization);
    }

    @GetMapping(params = "idMember")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> lentBooks(@RequestParam UUID idMember, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.LENT_BOOKS);
        return bookLoanService.getLentBooksByMember(idMember);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@RequestParam UUID idBookLoan, @RequestHeader  String authorization){
        securityService.validateAccess(authorization, Feature.RETURN_BOOK);
        bookLoanService.returnBookLoan(idBookLoan, authorization);
    }
}
