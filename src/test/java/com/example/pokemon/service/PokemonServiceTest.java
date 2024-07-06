package com.example.pokemon.service;

import com.example.pokemon.model.GetPokemonResponse;
import com.example.pokemon.model.PokemonApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PokemonServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private GetPokemonResponse createMockGetPokemonResponse() {
        GetPokemonResponse response = new GetPokemonResponse();
        response.setCount(1118);
        response.setNext("https://pokeapi.co/api/v2/pokemon?offset=10&limit=10");
        response.setPrevious(null);

        GetPokemonResponse.Results result1 = new GetPokemonResponse.Results();
        result1.setName("bulbasaur");
        result1.setUrl("https://pokeapi.co/api/v2/pokemon/1/");

        GetPokemonResponse.Results result2 = new GetPokemonResponse.Results();
        result2.setName("ivysaur");
        result2.setUrl("https://pokeapi.co/api/v2/pokemon/2/");

        response.setResults(Arrays.asList(result1, result2));
        return response;
    }

    private PokemonApiResponse createMockPokemonApiResponse() {
        PokemonApiResponse response = new PokemonApiResponse();
        response.setCount(1118);
        response.setNext("https://pokeapi.co/api/v2/pokemon?offset=10&limit=10");
        response.setPrevious(null);

        response.setResults(createMockGetPokemonResponse().getResults());
        return response;
    }

    @Test
    void getPaginatedPokemon_shouldReturnApiResponse() {
        PokemonApiResponse mockResponse = createMockPokemonApiResponse();
        when(restTemplate.getForObject(anyString(), any())).thenReturn(mockResponse);

        PokemonApiResponse response = pokemonService.getPaginatedPokemon(0, 10);

        assertNotNull(response);
    }
}
