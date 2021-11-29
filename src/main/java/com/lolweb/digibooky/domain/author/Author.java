package com.lolweb.digibooky.domain.author;

import java.util.UUID;

public class Author {

    private final UUID id;
    private final String firstName;
    private final String lastName;

    public Author(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
