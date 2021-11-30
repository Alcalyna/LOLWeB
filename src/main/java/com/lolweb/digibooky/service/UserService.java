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

    public void addNewMember(UserDto newMember) {
        userRepository.save(userMapper.convertDtoToUser(newMember));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
