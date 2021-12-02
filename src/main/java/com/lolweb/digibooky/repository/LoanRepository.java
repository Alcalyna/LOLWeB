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

    public Map<UUID, UUID> getMapOfBooksPerMember() {
        return bookMemberMap;
    }

    public Map<UUID, BookLoan> getMapOfLoans() {
        return activeLoans;
    }

    public List<BookLoan> getAllActiveLoans() {
        return activeLoans.values().stream().collect(Collectors.toList());
    }

    public BookLoan save(BookLoan bookLoan) {
        this.activeLoans.put(bookLoan.getId(), bookLoan);
        return bookLoan;
    }

    public void deleteBookLoan(UUID loanId, UUID bookId){
        this.activeLoans.remove(loanId);
        this.bookMemberMap.remove(bookId);
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

    public BookLoan getBookLoanById(UUID bookLoanId){
        return activeLoans.get(bookLoanId);
    }
    public UUID getBookByMemberId(UUID memberId){
        for(UUID id : bookMemberMap.keySet()){
           if(bookMemberMap.get(id) == memberId){
               return id;
           }
       }
       return null;
    }
}
