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

    public List<Book> getAllByIsbn(String isbn) {
        return booksInLibrary.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    public void initBooks() {
        Book book0 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Dalinh", "Luniel"))
                .withIsAvailable(true)
                .withIsbn("1234567894564")
                .withTitle("LOLWeB is the best group")
                .build();
        booksInLibrary.put(book0.getId(), book0);

        Book book1 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Rutim", "Lulian"))
                .withIsAvailable(true)
                .withIsbn("1234567894478")
                .withTitle("Is the best group")
                .build();
        booksInLibrary.put(book1.getId(), book1);

        Book book2 = Book.BookBuilder.bookBuilder()
                .withId((UUID.fromString("a6501d07-a051-437d-b074-08bf8be9f247")))
                .withAuthor(new Author("Dalinh", "Luniel"))
                .withIsAvailable(true)
                .withIsbn("1234567897878")
                .withTitle("Group by")
                .build();
        booksInLibrary.put(book2.getId(), book2);

        Book book3 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Marcel", "Proust"))
                .withIsAvailable(true)
                .withIsbn("9780812969641")
                .withTitle("In Search of Lost Time")
                .build();
        booksInLibrary.put(book3.getId(), book3);

        Book book4 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Jarod", "Rosello"))
                .withIsAvailable(true)
                .withIsbn("9781603094443")
                .withTitle("Red Panda & Moon Bear")
                .build();
        booksInLibrary.put(book4.getId(), book4);

        Book book5 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Joanne", "Rowling"))
                .withIsAvailable(true)
                .withIsbn("9780747546290")
                .withTitle("Harry Potter and The Prisoner of Azkaban")
                .build();
        booksInLibrary.put(book5.getId(), book5);

        Book book6 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Joanne", "Rowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .build();
        booksInLibrary.put(book6.getId(), book6);

        Book book7 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Joanne", "Rowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .build();
        booksInLibrary.put(book7.getId(), book7);

        Book book8 = Book.BookBuilder.bookBuilder()
                .withId()
                .withAuthor(new Author("Joanne", "Rowling"))
                .withIsAvailable(true)
                .withIsbn("9780747581109")
                .withTitle("Harry Potter and The Half-Blood Prince")
                .build();
        booksInLibrary.put(book8.getId(), book8);
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
