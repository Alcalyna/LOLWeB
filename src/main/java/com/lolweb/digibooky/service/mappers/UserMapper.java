package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.dtos.UserDto;

public class UserMapper {

    public static User convertDtoToUser(UserDto userDto) {
        return new User(userDto);
    }

    public static UserDto convertUserToDto (User user) {
        return new UserDto(user);
    }

}
