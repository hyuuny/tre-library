package com.hyuuny.trelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TreLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreLibraryApplication.class, args);
    }

}
