package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.loan.BookLoan;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class LoanRepository {

    private final Map<UUID, BookLoan> activeLoans = new HashMap<>();
    private final Map<UUID, UUID> bookMemberMap = new HashMap<>();

    public LoanRepository() {
    }

    public List<BookLoan> getAllActiveLoans() {
        return activeLoans.values().stream().collect(Collectors.toList());
    }

    public BookLoan save(BookLoan bookLoan) {
        this.activeLoans.put(bookLoan.getId(), bookLoan);
        return bookLoan;
    }

    public void deleteBookLoan(UUID loanId, UUID memberId){
        this.activeLoans.remove(loanId);
        this.bookMemberMap.remove(memberId);
    }

    public void saveBookMemberMap(UUID bookId, UUID memberId) {
        this.bookMemberMap.put(bookId, memberId);
    }

    public List<UUID> getAllLentBooksByMember(UUID memberId) {
        List<UUID> listOfBooks = new ArrayList<>();
        for(UUID bookId : bookMemberMap.keySet()) {
            if(bookMemberMap.get(bookId).equals(memberId)) {
                listOfBooks.add(bookId);
            }
        }
        return listOfBooks;
    }


}
