package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
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
    private final SecurityService securityService;

    public UserService(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    public void validateInput(User user) {
        String inss = user.getInss();
        EmailAddress emailAddress = user.getEmailAddress();
        String lastName = user.getLastName();
        String city = user.getAddress().getCity();
        if (inss == null || inss.trim().equals("")) {
            throw new IllegalArgumentException("The INSS should be filled!");
        }
        if (userRepository.getAllInss().contains(inss)) {
            throw new IllegalArgumentException("The INSS already exists!");
        }
        if (userRepository.getAllEmailAddress().contains(emailAddress)) {
            throw new IllegalArgumentException("The email address already exists!");
        }
        if (lastName == null || lastName.trim().equals("")) {
            throw new IllegalArgumentException("The last name should be filled!");
        }
        if (city == null || city.trim().equals("")) {
            throw new IllegalArgumentException("The city should be filled!");
        }
    }

    public UserDto addNewMember(CreateUserDto newUser) {
        User user = UserMapper.mapCreateUserDtoToUser(newUser);
        if (!newUser.getRole().equals(User.Role.MEMBER)) {
            throw new IllegalArgumentException("You can only register as a member!");
        }

        validateInput(user);
        userRepository.save(user);
        return UserMapper.mapUserToUserDto(user);
    }

    public UserDto addNewLibrarian(CreateUserDto newUser) {
        User user = UserMapper.mapCreateUserDtoToUser(newUser);
        if (!user.getRole().equals(User.Role.LIBRARIAN)) {
            throw new IllegalArgumentException("You can only create a librarian!");
        }

        validateInput(user);
        userRepository.save(UserMapper.mapCreateUserDtoToUser(newUser));
        return UserMapper.mapUserToUserDto(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User getUserById(UUID idMember) {
        return userRepository.getUsers().get(idMember);
    }
}
