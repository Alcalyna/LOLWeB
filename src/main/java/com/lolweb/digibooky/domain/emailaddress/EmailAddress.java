package com.lolweb.digibooky.domain.emailaddress;

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
}
