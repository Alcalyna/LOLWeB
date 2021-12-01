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
    private HashMap<UUID, User> users;
    private User admin;

    public UserRepository() {

        this.users = new HashMap<>();
        admin = User.UserBuilder.userBuilder()
                .withId()
                .withAddress(null)
                .withEmailAddress(new EmailAddress("admin", "lolweb.com"))
                .withInss("959595959577")
                .withFirstName("Admin")
                .withLastName("Istrator")
                .withRole(User.Role.MEMBER)
                .build();
        users.put(admin.getId(), admin);
    }

    public User save(User user){
        users.put(user.getId(),user);
        return user;
    }

    public List<User> getAll(){
        return users.values().stream().collect(Collectors.toList());
    }

    public User getUserById(UUID id) {
        return users.get(id);
    }

    public User getAdmin() {
        return admin;
    }

    public User getUser(String email) {
        return users.values().stream()
                .filter(user -> user.getEmailAddress().toString().equals(email))
                .findFirst().orElse(null);
    }
}
