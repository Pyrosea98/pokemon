package com.example.pokemon.controller;

import com.example.pokemon.model.GetPokemonRequest;
import com.example.pokemon.model.GetPokemonResponse;
import com.example.pokemon.model.PokemonApiResponse;
import com.example.pokemon.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class PokemonEndpointTest {

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonEndpoint pokemonEndpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private PokemonApiResponse createMockPokemonApiResponse() {
        PokemonApiResponse response = new PokemonApiResponse();
        response.setCount(1118);
        response.setNext("https://pokeapi.co/api/v2/pokemon?offset=10&limit=10");
        response.setPrevious(null);

        response.setResults(createMockGetPokemonResponse().getResults());
        return response;
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

    @Test
    void getPokemon_shouldReturnResponse() {
        GetPokemonRequest request = new GetPokemonRequest();
        request.setOffset(0);
        request.setLimit(10);

        JAXBElement<GetPokemonRequest> jaxbElement = new JAXBElement<>(new QName("http://example.com/pokemon", "getPokemonRequest"), GetPokemonRequest.class, request);

        PokemonApiResponse mockApiResponse = createMockPokemonApiResponse();
        when(pokemonService.getPaginatedPokemon(anyInt(), anyInt())).thenReturn(mockApiResponse);

        JAXBElement<GetPokemonResponse> response = pokemonEndpoint.getPokemon(jaxbElement);

        assertNotNull(response);
    }
}
