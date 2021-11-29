package com.lolweb.digibooky.domain.emailaddress;

import java.util.Objects;

public class EmailAddress {

    private final String username;
    private final String domain;

    public EmailAddress(String username, String domain) {
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
}
