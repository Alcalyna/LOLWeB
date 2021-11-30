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
        userService.addNewMember(newMember);
        return newMember;
    }

//    public UserDto registerLibrarian(){
//           }




}
