package com.lolweb.digibooky.domain.emailaddress;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lolweb.digibooky.exceptions.EmailNotValidException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonSerialize
public class EmailAddress {

    @JsonProperty("username")
    private final String username;
    @JsonProperty("domain")
    private final String domain;
    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    public EmailAddress(String username, String domain) {
        if(!isValidEmail(username + "@" + domain)) { throw new EmailNotValidException("This email address is not valid!");
        }
        this.username = username;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return username + "@" + domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(username, that.username) && Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, domain);
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
