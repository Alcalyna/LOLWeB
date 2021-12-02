package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAccess(String authorization, Feature feature) {
        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));
        String password = decodeUsernamePassword.substring(decodeUsernamePassword.indexOf(":") + 1);
        User user = UserRepository.getUser(email);
        if(user == null){
            // custom exception needed
            throw new IllegalArgumentException("user doesn't exist");
        }
        if(!user.getPassword().equals(password)){
            // custom exception needed
            throw new IllegalArgumentException("Wrong password");
        }
        if(!user.hasAccessTo(feature)){
            // custom exception needed
            throw new IllegalArgumentException("Not allowed to");
        }
    }

    public User getCurrentUser(String authorization) {
        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));
        String password = decodeUsernamePassword.substring(decodeUsernamePassword.indexOf(":") + 1);
        User user = UserRepository.getUser(email);
        return user;
    }
}
