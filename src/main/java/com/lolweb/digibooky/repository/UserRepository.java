package com.lolweb.digibooky.repository;

import com.lolweb.digibooky.domain.book.Book;
import com.lolweb.digibooky.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    private HashMap<UUID, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
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
}
