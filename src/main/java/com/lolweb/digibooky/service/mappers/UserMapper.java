package com.lolweb.digibooky.service.mappers;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.dtos.CreateUserDto;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User mapCreateUserDtoToUser(CreateUserDto createUserDto) {
        return User.UserBuilder.userBuilder()

        .withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withAddress(createUserDto.getAddress())
                .withInss(createUserDto.getInss())
                .withEmailAddress(createUserDto.getEmailAddress())
                .withRole(createUserDto.getRole())
                .withPassword(createUserDto.getPassword())
                .build();
    }

    public static UserDto mapUserToUserDto(User user) {

        return new UserDto()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmailAddress(user.getEmailAddress())
                .setPassword(user.getPassword())
                .setInss(user.getInss())
                .setRoleForDto(user.getRole())
                .setAddress(user.getAddress());

    }

}
