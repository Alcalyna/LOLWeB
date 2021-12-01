package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember (@RequestBody UserDto newMember) {
        userService.addNewMember(newMember);
        return newMember;
    }

    @RequestMapping(path="users/admin")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerLibrarian(@RequestBody  UserDto newLibrarian, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
        userService.addNewLibrarian(newLibrarian);
        return newLibrarian;
    }
}
