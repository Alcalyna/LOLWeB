package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private final HashMap<UUID, User> users = new HashMap<>();

    public UserRepository() {
        initUsers();
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public HashMap<UUID, User> getUsers() {
        return users;
    }

    public List<User> getAll() {
        return users.values().stream().collect(Collectors.toList());
    }


    public User getUserById(UUID id) {
        return users.get(id);
    }


    public User getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmailAddress().toString().equals(email))
                .findFirst().orElse(null);
    }

    public List<EmailAddress> getAllEmailAddress() {
        return users.values().stream()
                .map(user -> user.getEmailAddress())
                .collect(Collectors.toList());
    }

    public List<String> getAllInss() {
        return users.values().stream()
                .map(user -> user.getInss())
                .collect(Collectors.toList());
    }

    public void initUsers() {
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

        users.put(admin.getId(), admin);
        users.put(member.getId(), member);
        users.put(librarian.getId(), librarian);
        users.put(gerri.getId(), gerri);
        users.put(william.getId(), william);
        users.put(tim.getId(), tim);
    }
}
