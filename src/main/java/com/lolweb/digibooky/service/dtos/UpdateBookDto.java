package com.lolweb.digibooky.service.dtos;

import com.lolweb.digibooky.domain.user.User;
import com.lolweb.digibooky.repository.UserRepository;

import java.util.UUID;

public class UpdateBookDto {
    private UUID userId;
    private String bookIsbn;

    public UUID getUserId() {
        return userId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

}
