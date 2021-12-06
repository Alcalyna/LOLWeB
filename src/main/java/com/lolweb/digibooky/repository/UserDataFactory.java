package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDataFactory {

    public Map<UUID, User> getDefaultUsers() {
        User admin = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("c6fdc526-a853-4e5f-abfa-b3fa6b4c70e7"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Ghent").build())
                .withEmailAddress(new EmailAddress("admin", "lolweb.com"))
                .withPassword("admin")
                .withInss("959595959577")
                .withFirstName("Admin")
                .withLastName("Istrator")
                .withRole(User.Role.ADMIN)
                .build();

        User member = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("4812cf2f-07b6-4f1b-a982-88482f1c0df9"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Brussels").build())
                .withEmailAddress(new EmailAddress("member", "lolweb.com"))
                .withPassword("member")
                .withInss("151515151515")
                .withFirstName("Mem")
                .withLastName("Ber")
                .withRole(User.Role.MEMBER)
                .build();

        User librarian = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("2b03ba64-1cd7-4e77-9c78-6d3ad159fc35"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Mons").build())
                .withEmailAddress(new EmailAddress("librarian", "lolweb.com"))
                .withPassword("librarian")
                .withInss("95152601532")
                .withFirstName("Li")
                .withLastName("Brarian")
                .withRole(User.Role.LIBRARIAN)
                .build();

        User gerri = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("9f426ff3-ec41-4db9-8b71-56ec5c56ec21"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Namur").build())
                .withEmailAddress(new EmailAddress("gerri", "lolweb.com"))
                .withPassword("gerri")
                .withInss("4565987845213")
                .withFirstName("Ger")
                .withLastName("Ri")
                .withRole(User.Role.MEMBER)
                .build();

        User william = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("19c6dbfb-f92e-4c10-9338-828549402bed"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Liège").build())
                .withEmailAddress(new EmailAddress("william", "lolweb.com"))
                .withPassword("william")
                .withInss("4969877813")
                .withFirstName("Wil")
                .withLastName("Liam")
                .withRole(User.Role.MEMBER)
                .build();

        User tim = User.UserBuilder.userBuilder()
//                .withId(UUID.fromString("8e322772-9d1d-4541-95d6-84d2edce0acd"))
                .withAddress(Address.AddressBuilder.addressBuilder().withCity("Arlon").build())
                .withEmailAddress(new EmailAddress("tim", "lolweb.com"))
                .withPassword("tim")
                .withInss("4969877813")
                .withFirstName("Tim")
                .withLastName("V")
                .withRole(User.Role.MEMBER)
                .build();

        Map<UUID, User> result = new HashMap<>();
        result.put(admin.getId(), admin);
        result.put(member.getId(), member);
        result.put(librarian.getId(), librarian);
        result.put(gerri.getId(), gerri);
        result.put(william.getId(), william);
        result.put(tim.getId(), tim);
        return result;
    }
}
