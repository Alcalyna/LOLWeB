package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private final Map<UUID, User> users;

    public UserRepository(UserDataFactory userDataFactory) {
        users = userDataFactory.getDefaultUsers();
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Map<UUID, User> getUsers() {
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


}
