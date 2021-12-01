package com.lolweb.digibooky.api;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.service.SecurityService;
import com.lolweb.digibooky.service.UserService;
import com.lolweb.digibooky.service.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    private final UserService userService;
    private final SecurityService securityService;

    public AdminController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerLibrarian(@RequestBody  UserDto newLibrarian, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
        userService.addNewLibrarian(newLibrarian);
        return newLibrarian;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerAdmin(@RequestBody  UserDto newLibrarian, @RequestHeader String authorization) {
        securityService.validateAccess(authorization, Feature.REGISTER_LIBRARIAN);
        userService.addNewLibrarian(newLibrarian);
        return newLibrarian;
    }
}
