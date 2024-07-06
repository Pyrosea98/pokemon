package com.example.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.pokemon"})
public class PokemonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
    }
}
