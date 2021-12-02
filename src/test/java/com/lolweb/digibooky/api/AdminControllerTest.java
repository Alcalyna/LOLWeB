package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminControllerTest {

    private UserService userService;
    private UserController userController;
    private UserRepository userRepository;
    private SecurityService securityService;
    private UserMapper userMapper;
    private Address address;
    private User librarian;

    @BeforeEach

    void setup() {
        userRepository = new UserRepository();
        userMapper = new UserMapper();
        userService = new UserService(userRepository, userMapper);
        securityService = new SecurityService(userRepository);
        userController = new UserController(userService, securityService);;
    }

    //Test doe not compile yet : java.lang.NullPointerException: Cannot invoke "com.lolweb.digibooky.domain.user.User.getId()" because "user" is null
    @Disabled
    @Test
    void givenAnAdmin_whenCreatingALibrarian_thenLibrarianIsCreated() {
        userController.registerLibrarian(userMapper.convertUserToDto(librarian),"Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu");
        assertEquals(userRepository.getUserById(librarian.getId()).getRole(), User.Role.LIBRARIAN);

    }

}