package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.CreateUserDto;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerMember (@RequestBody CreateUserDto newMember) {
         return userService.addNewMember(newMember);
    }

    @RequestMapping(path="/admin")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerLibrarian(@RequestBody  CreateUserDto newLibrarian, @RequestHeader String authorization) {
        logger.debug("Method not running properly");
        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
        return userService.addNewLibrarian(newLibrarian);

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
