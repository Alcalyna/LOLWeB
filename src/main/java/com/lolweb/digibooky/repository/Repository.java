package com.lolweb.digibooky.repository;

public class Repository {
    public static void initAll() {
        BookRepository.initBooks();
        UserRepository.initUsers();
    }
}
