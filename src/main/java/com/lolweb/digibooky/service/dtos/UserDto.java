package com.lolweb.digibooky.service.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;

import java.util.UUID;

@JsonSerialize
public class UserDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private String password;
    private String inss;
    private User.Role role;
    private final Address address;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.address = user.getAddress();
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

    public Address getAddress() {
        return address;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public String getInss() {
        return inss;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) { this.role = role; }
}
