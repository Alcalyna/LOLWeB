package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember (@RequestBody UserDto newMember) {
        userService.addNewMember(newMember);
        return newMember;
    }

    @RequestMapping(path="/admin")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerLibrarian(@RequestBody  UserDto newLibrarian, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
        userService.addNewLibrarian(newLibrarian);
        return newLibrarian;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path="")
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserDto registerAdmin(@RequestBody  UserDto newAdmin, @RequestHeader String authorization) {
//        // todo change to REGISTER_ADMIN
//        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
//        userService.addNewLibrarian(newAdmin);
//        return newAdmin;
//    }
}
