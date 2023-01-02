package com.example.alba_prokect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlbaProkectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbaProkectApplication.class, args);
    }

}
