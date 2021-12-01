package com.lolweb.digibooky;

import com.lolweb.digibooky.repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigibookyLolWeBApplication {

    public static void main(String[] args) {
        Repository.initAll();
        SpringApplication.run(DigibookyLolWeBApplication.class, args);
    }

}
