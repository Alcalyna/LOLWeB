package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertDtoToUser(UserDto userDto) {
        return new User(userDto);
    }

    public UserDto convertUserToDto (User user) {
        return new UserDto(user);
    }

}
