package com.lolweb.digibooky.domain.user;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.service.dtos.UserDto;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private final String inss;
    private Role role;
    private final Address address;

    public User(UserBuilder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        emailAddress = builder.emailAddress;
        inss = builder.inss;
        role = builder.role;
        address = builder.address;
    }

    public User (UserDto userDto) {
        id = userDto.getId();
        firstName = userDto.getFirstName();
        lastName = userDto.getLastName();
        emailAddress = userDto.getEmailAddress();
        inss = userDto.getInss();
        role = userDto.getRole();
        address = userDto.getAddress();
    }
    public enum Role {
        ADMIN,
        LIBRARIAN,
        MEMBER
    }

    public static final class UserBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private EmailAddress emailAddress;
        private String inss;
        private Role role;
        private Address address;

        private UserBuilder() {
        }

        public static UserBuilder userBuilder() {
            return new UserBuilder();
        }

        public User build() {
            return new User(this);
        }

        public UserBuilder withId() {
            this.id = UUID.randomUUID();
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserBuilder withInss(String inss) {
            this.inss = inss;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = Role.MEMBER;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }
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

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public String getInss() {
        return inss;
    }

    public Role getRole() {
        return role;
    }

    public Address getAddress() {
        return address;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
