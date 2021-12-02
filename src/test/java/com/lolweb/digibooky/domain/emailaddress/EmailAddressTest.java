package com.lolweb.digibooky.domain.emailaddress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailAddressTest {

    @Test
    void testToString() {
        // GIVEN
        EmailAddress emailAddress = new EmailAddress("julinh", "lolweb.com");

        // THEN
        Assertions.assertEquals("julinh@lolweb.com", emailAddress.toString());
    }
}