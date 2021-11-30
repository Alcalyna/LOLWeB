package com.lolweb.digibooky.service;

import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.dtos.UserDto;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void addNewUser(UserDto newUser) {
        userRepository.save(userMapper.convertDtoToUser(newUser));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
