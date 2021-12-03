package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.author.Author;
import com.lolweb.digibooky.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookRepository {
    private final HashMap<UUID, Book> booksInLibrary = new HashMap<>();

    public BookRepository() {
        initBooks();
    }

    public Book save(Book book){
        booksInLibrary.put(book.getId(),book);
        return book;
    }

    public HashMap<UUID, Book> getBooksInLibrary() {
        return booksInLibrary;
    }

    public List<Book> getAll(){
        return booksInLibrary.values().stream().collect(Collectors.toList());
    }

    public Book getById(UUID id) {
        return booksInLibrary.get(id);
    }

    public Book getBookByIsbn(String isbn) {
        return getAll().stream().filter(book -> book.getIsbn().equals(isbn))
                .collect(Collectors.toList()).get(0);
    }

    public List<Book> getAllByIsbn(String isbn) {
        return booksInLibrary.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    public void initBooks() {
        Book book0 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("3c1f42c1-9281-4df8-be8c-5bd575920e56"))
                .withAuthor(new Author("Dalinh", "Luniel"))
                .withIsAvailable(true)
                .withIsbn("1234567894564")
                .withTitle("LOLWeB is the best group")
                .withSummary("Linh Or Lose, We Booze!")
                .build();
        booksInLibrary.put(book0.getId(), book0);

        Book book1 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("2e59b172-0900-435b-ac4b-eb3174e157d9"))
                .withAuthor(new Author("Rutim", "Lulian"))
                .withIsAvailable(true)
                .withIsbn("1234567894478")
                .withTitle("Is the best group")
                .withSummary("LOLWeB")
                .build();
        booksInLibrary.put(book1.getId(), book1);

        Book book2 = Book.BookBuilder.bookBuilder()
                .withId((UUID.fromString("a6501d07-a051-437d-b074-08bf8be9f247")))
                .withAuthor(new Author("Dalinh", "Luniel"))
                .withIsAvailable(true)
                .withIsbn("1234567897878")
                .withTitle("Group by")
                .withSummary("SQL, mySQL, SQLServer, PostgreSQL")
                .build();
        booksInLibrary.put(book2.getId(), book2);

        Book book3 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("c0e8ba59-3ce1-4663-953b-5343118dbb9d"))
                .withAuthor(new Author("Marcel", "Proust"))
                .withIsAvailable(true)
                .withIsbn("9780812969641")
                .withTitle("In Search of Lost Time")
                .withSummary("In Search of Lost Time follows the narrator's recollections of childhood and experiences into adulthood in the late 19th-century and early 20th-century high-society France, while reflecting on the loss of time and lack of meaning in the world. The novel began to take shape in 1909.")
                .build();
        booksInLibrary.put(book3.getId(), book3);

        Book book4 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("eed39bec-0cda-4e4b-ae65-c6cdfae6fa79"))
                .withAuthor(new Author("Kungfu", "Panda"))
                .withIsAvailable(true)
                .withIsbn("9781603094443")
                .withTitle("Red Panda & Moon Bear")
                .withSummary("Say no to anti-panda racism!")
                .build();
        booksInLibrary.put(book4.getId(), book4);

        Book book5 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("5e709f49-c93b-4ed8-9097-9366ebe4cad0"))
                .withAuthor(new Author("J.K.", "Bowling"))
                .withIsAvailable(true)
                .withIsbn("9780747546290")
                .withTitle("Harry Potter and The Prisoner of Switchfully")
                .withSummary("Do you like Harry Potter? - Because I a-Dumbledore you!")
                .build();
        booksInLibrary.put(book5.getId(), book5);

        Book book6 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("89d94c2e-1cb9-47d1-8444-0c4a33028f0e"))
                .withAuthor(new Author("J.K.", "Bowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withSummary("What do you call an electrocuted Dark Lord? - A Volt-demort.")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .build();
        booksInLibrary.put(book6.getId(), book6);

        Book book7 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("0423e2fd-b5be-46ba-9c9f-1b667a9fc2cb"))
                .withAuthor(new Author("J.K.", "Bowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .withSummary("What do you call an electrocuted Dark Lord? - A Volt-demort.")
                .build();
        booksInLibrary.put(book7.getId(), book7);

        Book book8 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("6feef42a-63a6-4722-a183-bde4a0882a4c"))
                .withAuthor(new Author("J.K.", "Bowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withSummary("What do you call an electrocuted Dark Lord? - A Volt-demort.")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .build();
        booksInLibrary.put(book8.getId(), book8);

        Book book9 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("d8d35d60-0f3a-43b5-9f44-b2b24a3e7542"))
                .withAuthor(new Author("Tom", "Jerry"))
                .withIsAvailable(true)
                .withIsbn("230517050906")
                .withSummary("Cannibal Lecter - How to cook gambas with sweet and sour sauce")
                .withTitle("The Silence of The Shrimps")
                .build();
        booksInLibrary.put(book9.getId(), book9);

        Book book10 = Book.BookBuilder.bookBuilder()
                .withId(UUID.fromString("e2354f0d-c348-476a-99bb-5c6da9b2d4ff"))
                .withAuthor(new Author("Gerri", "Jerry"))
                .withIsAvailable(true)
                .withIsbn("9874562106579")
                .withSummary("How to cheat at DnD")
                .withTitle("Dungeon and Dragons Master (branch)")
                .build();
        booksInLibrary.put(book9.getId(), book10);
    }

    public List<Book> getBookByAuthor(String authorName) {
        List<Book> books = this.getAll();
        List<Book> results = new ArrayList<>();
        authorName = authorName.toLowerCase();
        for(Book book : books) {
            String firstName = book.getAuthor().getFirstName().toLowerCase();
            String lastName = book.getAuthor().getLastName().toLowerCase();
            String fullName = book.getAuthor().getFullName().toLowerCase();
            if (authorName.equals(firstName) || authorName.equals(lastName) || authorName.equals(fullName)) {
                results.add(book);
            }
        }
        return results;
    }

}
