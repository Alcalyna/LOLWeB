package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.dtos.CreateUserDto;
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

    public UserDto addNewMember(CreateUserDto newUser) {
        User user = UserMapper.mapCreateUserDtoToUser(newUser);
        if (newUser.getRole().equals(User.Role.MEMBER)) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("You can only register as a member");
        }
        return UserMapper.mapUserToUserDto(user);
    }

    public UserDto addNewLibrarian(CreateUserDto newUser) {
        User user = UserMapper.mapCreateUserDtoToUser(newUser);
        if (newUser.getRole().equals(User.Role.LIBRARIAN) || newUser.getRole().equals(User.Role.ADMIN)) {
            userRepository.save(UserMapper.mapCreateUserDtoToUser(newUser));

        } else {
            throw new IllegalArgumentException("You are not allowed to create a librarian");
        }
        return UserMapper.mapUserToUserDto(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getUserById(UUID idMember) {
        return userRepository.getUsers().get(idMember);
    }
}
