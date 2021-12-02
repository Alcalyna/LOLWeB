package com.lolweb.digibooky.domain.book;

import com.lolweb.digibooky.domain.author.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class BookTest {

    Book book;
    Author author;

    @BeforeEach
    public void init() {
        author = new Author("Rutim", "Luniel");

        book = Book.BookBuilder.bookBuilder()
                .withAuthor(author)
                .withId()
                .withIsAvailable(true)
                .withIsbn("1234567890000")
                .withTitle("We are the best group ever")
                .build();
    }

    @Test
    void testGetId_ReturnUUIDClass() {
        Assertions.assertEquals(UUID.class, book.getId().getClass());
    }

    @Test
    void getIsbn() {
        Assertions.assertEquals("1234567890000", book.getIsbn());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals("We are the best group ever", book.getTitle());
    }

    @Test
    void testGetFullNameAuthorAndClassAuthor() {
        Assertions.assertEquals("Rutim", book.getAuthor().getFirstName());
        Assertions.assertEquals("Luniel", book.getAuthor().getLastName());
        Assertions.assertEquals(UUID.class, book.getAuthor().getId().getClass());
    }

    @Test
    void isAvailable() {
        Assertions.assertEquals(true, book.isAvailable());
    }
}