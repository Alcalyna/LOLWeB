package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;

public class CreateUserDto {
    private  String firstName;
    private  String lastName;
    private EmailAddress emailAddress;
    private  String password;
    private  String inss;
    private User.Role role;
    private Address address;

    public String getFirstName() {
        return firstName;
    }

    public CreateUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public CreateUserDto setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getInss() {
        return inss;
    }

    public CreateUserDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public User.Role getRole() {
        return role;
    }

    public CreateUserDto setRole(User.Role role) {
        this.role = role;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public CreateUserDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress=" + emailAddress +
                ", password='" + password + '\'' +
                ", inss='" + inss + '\'' +
                ", role=" + role +
                ", address=" + address +
                '}';
    }
}
