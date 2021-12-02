package com.lolweb.digibooky.domain.user;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UserTest {

    Address address;

    @BeforeEach
    public void init() {
        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("LolWeb street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("29")
                .build();
    }

    @Test
    public void testingBuilder() {
        // GIVEN
        User user = User.UserBuilder.userBuilder()
                .withId()
                .withAddress(address)
                .withEmailAddress(new EmailAddress("julinh", "lolweb.com"))
                .withInss("959595959595")
                .withFirstName("Rutim")
                .withLastName("Luniel")
                .withRole(User.Role.ADMIN)
                .build();

        Address expectedAddress = Address.AddressBuilder.addressBuilder()
                .withStreetName("LolWeb street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("29")
                .build();

        // THEN
        Assertions.assertEquals(UUID.class, user.getId().getClass());

        Assertions.assertEquals(expectedAddress, user.getAddress());
        Assertions.assertEquals(new EmailAddress("julinh", "lolweb.com"), user.getEmailAddress());
        Assertions.assertEquals("959595959595", user.getInss());
        Assertions.assertEquals("Rutim", user.getFirstName());
        Assertions.assertEquals("Luniel", user.getLastName());
        Assertions.assertEquals(User.Role.ADMIN, user.getRole());
    }
}