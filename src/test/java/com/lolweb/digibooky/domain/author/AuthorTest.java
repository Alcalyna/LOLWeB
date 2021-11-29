package com.lolweb.digibooky.domain.author;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AuthorTest {
    Author author;

    @BeforeEach
    public void init() {
        author = new Author("RuTim", "LuNiel");
    }

    @Test
    public void givenAnAuthor_CallingGetFirstNameMethod_ReturnFirstName() {
        Assertions.assertEquals("RuTim", author.getFirstName());
    }

    @Test
    public void givenAnAuthor_CallingGetLastNameMethod_ReturnLastName() {
        Assertions.assertEquals("LuNiel", author.getLastName());
    }

    @Test
    public void givenAnAuthor_CallingGetIdMethod_ReturnUUIDClass() {
        Assertions.assertEquals(UUID.class, author.getId().getClass());
    }
}