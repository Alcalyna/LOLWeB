package com.lolweb.digibooky.domain.address;

import java.util.Objects;

public class Address {

    private final String streetName;
    private final String streetNumber;
    private final String postCode; //need to validate
    private final String city;


    public Address (AddressBuilder builder) {
        streetName = builder.streetName;
        streetNumber = builder.streetNumber;
        postCode = builder.postCode;
        city = builder.city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public static final class AddressBuilder {
        private String streetName;
        private String streetNumber;
        private String postCode;
        private String city;

        private AddressBuilder() {
        }

        //static factory method
        public static AddressBuilder addressBuilder() {
            return new AddressBuilder();
        }

        public Address build() {
            return new Address(this);
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetName, address.streetName) && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(postCode, address.postCode) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postCode, city);
    }
}
