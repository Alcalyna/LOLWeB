package com.lolweb.digibooky.repository;

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

    public void initUsers() {
        User admin = User.UserBuilder.userBuilder()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("admin", "lolweb.com"))
                .withPassword("admin")
                .withInss("959595959577")
                .withFirstName("Admin")
                .withLastName("Istrator")
                .withRole(User.Role.ADMIN)
                .build();

        User member = User.UserBuilder.userBuilder()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("member", "lolweb.com"))
                .withPassword("member")
                .withInss("151515151515")
                .withFirstName("Mem")
                .withLastName("Ber")
                .withRole(User.Role.MEMBER)
                .build();

        User librarian = User.UserBuilder.userBuilder()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("librarian", "lolweb.com"))
                .withPassword("librarian")
                .withInss("95152601532")
                .withFirstName("Li")
                .withLastName("Brarian")
                .withRole(User.Role.LIBRARIAN)
                .build();

        User gerri = User.UserBuilder.userBuilder()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("gerri", "lolweb.com"))
                .withPassword("gerri")
                .withInss("4565987845213")
                .withFirstName("Ger")
                .withLastName("Ri")
                .withRole(User.Role.MEMBER)
                .build();

        User william = User.UserBuilder.userBuilder()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("william", "lolweb.com"))
                .withPassword("william")
                .withInss("4969877813")
                .withFirstName("Wil")
                .withLastName("Liam")
                .withRole(User.Role.MEMBER)
                .build();

        User tim = User.UserBuilder.userBuilder()
                .withAddress(null)
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
