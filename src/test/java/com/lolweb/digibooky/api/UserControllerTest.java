package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.exceptions.UserNotAuthorizedException;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.CreateBookDto;
import com.lolweb.digibooky.service.dtos.CreateUserDto;
import com.lolweb.digibooky.service.dtos.UserDto;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.objenesis.instantiator.util.UnsafeUtils;

import javax.validation.constraints.AssertTrue;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private UserRepository userRepository;
    private SecurityService securityService;
    private UserMapper userMapper;
    private Address address;
    private User member;
    private User librarian;
    private CreateUserDto createLibrarianDto;
    private CreateUserDto createMemberDto;


    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
        userMapper = new UserMapper();
        userService = new UserService(userRepository, userMapper);
        securityService = new SecurityService(userRepository);
        userController = new UserController(userService, securityService);
        initUsers();
    }

    @Test
    void GivenUser_WhenRegisteredAsAMember_Then_NewMemberAdded() {
        UUID actual = userController.registerMember(createMemberDto).getId();
        assertTrue(userRepository.getUsers().containsKey(actual));

    }

    @Test
    void GivenUser_WhenRegisteredAsAMember_Then_RoleIsMember() {
        UUID actual =  userController.registerMember(createMemberDto).getId();

        assertEquals(userRepository.getUserById(actual).getRole(), User.Role.MEMBER);
    }

    @Test
    void GivenLibrarian_whenRegisteredALibrarian_then_ThrowsUserNotAuthorizedException() {

        Throwable exception = catchThrowable(() -> userController
                .registerLibrarian(createLibrarianDto, "Basic bGlicmFyaWFuQGxvbHdlYi5jb206bGlicmFyaWFu"));

        //THEN
        org.assertj.core.api.Assertions.assertThat(exception).isInstanceOf(UserNotAuthorizedException.class)
                .hasMessage("Not allowed to");
    }


    private void initUsers() {
        // member
        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("nightelm street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("15")
                .build();

        member = User.UserBuilder.userBuilder()
                .withAddress(address)
                .withEmailAddress(new EmailAddress("julinh", "lolweb.com"))
                .withInss("959595959595")
                .withFirstName("Rutim")
                .withLastName("Luniel")
                .withRole(User.Role.MEMBER)
                .build();
        // librarian

        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("LolWeb street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("29")
                .build();

        librarian = User.UserBuilder.userBuilder()
                .withAddress(address)
                .withEmailAddress(new EmailAddress("Dave", "lolweb.com"))
                .withInss("959595959595")
                .withFirstName("Dave")
                .withLastName("Bookman")
                .withRole(User.Role.LIBRARIAN)
                .build();

        createLibrarianDto = new CreateUserDto()
                .setFirstName("Laura")
                .setLastName("Smith")
                .setAddress(address)
                .setEmailAddress(new EmailAddress("laura", "lolweb.com"))
                .setInss("126598745")
                .setRole(User.Role.LIBRARIAN)
                .setPassword("hellolibrarian");

        createMemberDto = new CreateUserDto()
                .setFirstName("Paul")
                .setLastName("Brown")
                .setAddress(address)
                .setEmailAddress(new EmailAddress("paul", "lolweb.com"))
                .setInss("4587298745")
                .setRole(User.Role.MEMBER)
                .setPassword("hellomember");


    }

}