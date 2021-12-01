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
    private final static HashMap<UUID, User> users = new HashMap<>();

    public UserRepository() {
    }

    public User save(User user){
        users.put(user.getId(),user);
        return user;
    }

    public static List<User> getAll(){
        return users.values().stream().collect(Collectors.toList());
    }

    public User getUserById(UUID id) {
        return users.get(id);
    }


    public static User getUser(String email) {
        return users.values().stream()
                .filter(user -> user.getEmailAddress().toString().equals(email))
                .findFirst().orElse(null);
    }

    public static void initUsers(){
        User admin = User.UserBuilder.userBuilder()
                .withId()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("admin", "lolweb.com"))
                .withPassword("admin")
                .withInss("959595959577")
                .withFirstName("Admin")
                .withLastName("Istrator")
                .withRole(User.Role.ADMIN)
                .build();


        User member = User.UserBuilder.userBuilder()
                .withId()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("member", "lolweb.com"))
                .withPassword("member")
                .withInss("151515151515")
                .withFirstName("Mem")
                .withLastName("Ber")
                .withRole(User.Role.MEMBER)
                .build();

        User librarian = User.UserBuilder.userBuilder()
                .withId()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("librarian", "lolweb.com"))
                .withPassword("librarian")
                .withInss("95152601532")
                .withFirstName("Li")
                .withLastName("Brarian")
                .withRole(User.Role.LIBRARIAN)
                .build();

        users.put(admin.getId(), admin);
        users.put(member.getId(), member);
        users.put(librarian.getId(), librarian);
    }
}
