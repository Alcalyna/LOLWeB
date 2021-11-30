package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember (@RequestBody UserDto newMember) {
        userService.addNewUser(newMember);
        return newMember;
    }

   /*
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerLibrarian(@RequestBody  UserDto newLibrarian) {
        //check if user is an admin
        newLibrarian.setRole(User.Role.LIBRARIAN);
        userService.addNewUser(newLibrarian);
        return newLibrarian;
    }*/
}
