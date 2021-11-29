package com.lolweb.digibooky.domain.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    public void creatingAnAddressFromBuilder() {

        // GIVEN
        Address address = Address.AddressBuilder.addressBuilder()
                .withStreetName("LolWeb street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("29")
                .build();

        // THEN
        Assertions.assertEquals("Switchfully City", address.getCity());
        Assertions.assertEquals("LolWeb street", address.getStreetName());
        Assertions.assertEquals("2021", address.getPostCode());
        Assertions.assertEquals("29", address.getStreetNumber());
    }
}