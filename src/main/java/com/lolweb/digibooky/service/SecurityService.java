package com.lolweb.digibooky.service;

import com.lolweb.digibooky.domain.feature.Feature;
import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.exceptions.PasswordNotValidException;
import com.lolweb.digibooky.exceptions.UserDoesNotExistException;
import com.lolweb.digibooky.exceptions.UserNotAuthorizedException;
import com.lolweb.digibooky.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
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
        User user = userRepository.getUserByEmail(email);
        if(user == null){
            throw new UserDoesNotExistException();
        }
        if(!user.getPassword().equals(password)){
            throw new PasswordNotValidException("Wrong password");
        }
        if(!user.hasAccessTo(feature)){
            throw new AccessDeniedException("You are not allowed to do this action.");
        }
    }

    public User getCurrentUser(String authorization) {
        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));
        User user = userRepository.getUserByEmail(email);
        return user;
    }
}
