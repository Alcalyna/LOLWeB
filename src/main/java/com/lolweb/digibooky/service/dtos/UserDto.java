package com.lolweb.digibooky.service.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;

import java.util.UUID;

@JsonSerialize
public class UserDto {

    private  UUID id;
    private  String firstName;
    private  String lastName;
    private  EmailAddress emailAddress;
    private  String password;
    private  String inss;
    private User.Role role;
    private Address address;

    public UserDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDto setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public UserDto setAddress(Address address) {
        this.address = address;
        return this;
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

    public UserDto setRoleForDto(User.Role role){
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress=" + emailAddress +
                ", password='" + password + '\'' +
                ", inss='" + inss + '\'' +
                ", role=" + role +
                ", address=" + address +
                '}';
    }
}
