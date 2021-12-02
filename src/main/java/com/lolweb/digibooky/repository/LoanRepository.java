package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.loan.BookLoan;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class LoanRepository {

    private static final Map<UUID, BookLoan> activeLoans = new HashMap<>();
    private static final Map<UUID, UUID> bookMemberMap = new HashMap<>();

    public static List<BookLoan> getAll() {
        return activeLoans.values().stream().collect(Collectors.toList());
    }

    public static BookLoan save(BookLoan bookLoan) {
        activeLoans.put(bookLoan.getId(), bookLoan);
        return bookLoan;
    }

    public static void saveBookMemberMap(UUID bookId, UUID membreID) {
        bookMemberMap.put(bookId, membreID);
    }

    public static List<UUID> getAllLentBooksByMember(UUID memberId) {
        List<UUID> listOfBooks = new ArrayList<>();
        for(UUID bookId : bookMemberMap.values()) {
            if(bookMemberMap.get(bookId).equals(memberId)) {
                listOfBooks.add(bookId);
            }
        }
        return listOfBooks;
    }
}
