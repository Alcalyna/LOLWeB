package com.lolweb.digibooky;

import com.lolweb.digibooky.repository.BookRepository;
import com.lolweb.digibooky.repository.Repository;
import com.lolweb.digibooky.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigibookyLolWeBApplication {

    public static void main(String[] args) {
        Repository.initAll();
        SpringApplication.run(DigibookyLolWeBApplication.class, args);
    }

}
