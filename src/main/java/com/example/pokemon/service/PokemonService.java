package com.example.pokemon.service;

import com.example.pokemon.model.PokemonApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonApiResponse getPaginatedPokemon(int offset, int limit) {
        String url = "https://pokeapi.co/api/v2/pokemon?offset=" + offset + "&limit=" + limit;
        return restTemplate.getForObject(url, PokemonApiResponse.class);
    }
}
