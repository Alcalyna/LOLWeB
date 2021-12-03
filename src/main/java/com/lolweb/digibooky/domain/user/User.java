package com.lolweb.digibooky.domain.user;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.feature.Feature;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private String password;
    private final String inss;
    private Role role;
    private final Address address;

    public User(UserBuilder builder) {
        id = UUID.randomUUID();
        firstName = builder.firstName;
        lastName = builder.lastName;
        emailAddress = builder.emailAddress;
        password = builder.password;
        inss = builder.inss;
        role = builder.role;
        address = builder.address;
    }


    public enum Role {
        ADMIN(List.of(Feature.REGISTER_LIBRARIAN, Feature.REGISTER_BOOK, Feature.LENT_BOOKS)),
        LIBRARIAN(List.of(Feature.REGISTER_BOOK, Feature.LENT_BOOKS)),
        MEMBER(List.of(Feature.BORROW_BOOK, Feature.RETURN_BOOK));

        private List<Feature> featureList;

        Role(List<Feature> featureList) {
            this.featureList = featureList;
        }

        public boolean containsFeature(Feature feature){
            return featureList.contains(feature);
        }
    }


    public static final class UserBuilder {

        private String firstName;
        private String lastName;
        private EmailAddress emailAddress;
        private String password;
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
            this.role = role;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasAccessTo(Feature feature){
        return this.role.containsFeature(feature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
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
