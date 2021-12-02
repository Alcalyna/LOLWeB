package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.dtos.UserDto;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void addNewMember(UserDto newUser) {
        if(newUser.getRole().equals(User.Role.MEMBER)) {
            userRepository.save(userMapper.convertDtoToUser(newUser));
        } else {
            throw new IllegalArgumentException("You can only register as a member");
        }
    }

    public void addNewLibrarian(UserDto newUser){
        newUser.setRole(User.Role.LIBRARIAN);
        userRepository.save(userMapper.convertDtoToUser(newUser));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getUserById(UUID idMember) {
        return userRepository.getUsers().get(idMember);
    }
}
