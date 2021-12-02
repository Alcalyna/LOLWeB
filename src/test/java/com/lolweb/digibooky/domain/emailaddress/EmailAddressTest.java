package com.lolweb.digibooky.domain.emailaddress;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailAddressTest {

    @Test
    void testToString() {
        // GIVEN
        EmailAddress emailAddress = new EmailAddress("julinh", "lolweb.com");

        // THEN
        assertEquals("julinh@lolweb.com", emailAddress.toString());
    }

    @Test
    void GivenAGoodAddress_Then_NoExceptionThrown() {
        // GIVEN
        EmailAddress emailAddress = new EmailAddress("julinh", "lolweb.com");

        //THEN
        assertTrue(EmailAddress.isValidEmail(emailAddress.toString()));

    }
}