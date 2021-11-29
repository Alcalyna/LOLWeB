package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.dtos.UserDto;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserService {

    private final Map<UUID, User> users = new HashMap<>();

    public void addNewMember(UserDto newMember) {
        users.put(newMember.getId(), UserMapper.convertDtoToUser(newMember));
    }
}
