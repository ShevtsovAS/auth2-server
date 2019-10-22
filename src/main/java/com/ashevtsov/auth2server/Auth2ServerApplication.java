package com.ashevtsov.auth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Auth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Auth2ServerApplication.class, args);
    }

}
