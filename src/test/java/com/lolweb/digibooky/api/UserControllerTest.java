package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.address.Address;
import com.lolweb.digibooky.domain.emailaddress.EmailAddress;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private Address address;
    private User member;
    private User librarian;

    @BeforeEach

    void setup() {
        userRepository = new UserRepository();
        userMapper = new UserMapper();
        userService = new UserService(userRepository, userMapper);
        userController = new UserController(userService);
        initUsers();
    }

    @Test
    void GivenUser_WhenRegisteredAsAMember_Then_NewMemberAdded() {
        userController.registerMember(userMapper.convertUserToDto(member));
        assertTrue(userRepository.getAll().size() > 1);
    }

    @Test
    void GivenUser_WhenRegisteredAsAMember_Then_RoleIsMember() {
        userController.registerMember(userMapper.convertUserToDto(member));
        assertEquals(userRepository.getUserById(member.getId()).getRole(), User.Role.MEMBER);
    }
    /*
    @Test
    void GivenUser_whenRegisteredAsALibrarian_then_RoleIsLibrarian(){
        userController.registerLibrarian(userMapper.convertUserToDto(librarian));
        assertEquals(userRepository.getUserById(librarian.getId()).getRole(), User.Role.LIBRARIAN);
    }*/


    private void initUsers(){
        // member
        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("nightelm street")
                .withCity("Switchfully City")
                .withPostCode("2021")
                .withStreetNumber("15")
                .build();

        member = User.UserBuilder.userBuilder()
                .withId()
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
                .withId()
                .withAddress(address)
                .withEmailAddress(new EmailAddress("Dave", "lolweb.com"))
                .withInss("959595959595")
                .withFirstName("Dave")
                .withLastName("Bookman")
                .withRole(User.Role.LIBRARIAN)
                .build();

    }

}