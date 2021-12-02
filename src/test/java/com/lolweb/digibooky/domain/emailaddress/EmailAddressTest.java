package com.lolweb.digibooky.domain.emailaddress;

import com.lolweb.digibooky.exceptions.EmailNotValidException;
import com.lolweb.digibooky.exceptions.UserNotAuthorizedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Email;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
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
    void givenAGoodAddress_Then_NoExceptionThrown() {
        // GIVEN
        EmailAddress emailAddress = new EmailAddress("julinh", "lolweb.com");

        //THEN
        assertTrue(EmailAddress.isValidEmail(emailAddress.toString()));

    }

    @Test
    void givenWrongEmailAddress_ThenExceptionThrown(){

        Throwable exception = catchThrowable(() -> new EmailAddress("juda", "lolweb"));

        //THEN
        Assertions.assertThat(exception).isInstanceOf(EmailNotValidException.class)
                .hasMessage("This email address is not valid!");
    }
}