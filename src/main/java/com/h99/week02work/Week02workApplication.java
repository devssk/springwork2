package com.h99.week02work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week02workApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week02workApplication.class, args);
    }

}
