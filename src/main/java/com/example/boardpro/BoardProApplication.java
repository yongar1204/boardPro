package com.example.boardpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardProApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardProApplication.class, args);
    }

}
