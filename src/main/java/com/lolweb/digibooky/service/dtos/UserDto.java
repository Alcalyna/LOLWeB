package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;

import java.util.UUID;

public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private EmailAddress emailAddress;
    private String inss;
    private User.Role role;
    private Address address;

    public UserDto(String firstName, String lastName, EmailAddress emailAddress, String inss, User.Role role, Address address) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.inss = inss;
        this.role = role;
        this.address = address;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
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
}
