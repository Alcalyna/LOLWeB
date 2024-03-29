package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
    User user;

    @BeforeEach
    void init() {
        user = User.UserBuilder.userBuilder()
                .withRole(User.Role.LIBRARIAN)
                .withFirstName("Li")
                .withLastName("Brarian")
                .withInss("95849565231")
                .withPassword("librarian")
                .withAddress(null)
                .withEmailAddress(new EmailAddress("librarian2", "lolweb.com"))
                .build();
    }

    @Test
    void getUser() {
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);

        Assertions.assertEquals(user, userRepository.getUserByEmail("librarian2@lolweb.com"));
    }
}