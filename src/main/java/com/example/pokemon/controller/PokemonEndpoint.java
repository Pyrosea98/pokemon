package com.example.pokemon.controller;

import com.example.pokemon.model.GetPokemonRequest;
import com.example.pokemon.model.GetPokemonResponse;
import com.example.pokemon.model.PokemonApiResponse;
import com.example.pokemon.service.PokemonService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/pokemon";
    private final PokemonService pokemonService;

    public PokemonEndpoint(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonRequest")
    @ResponsePayload
    public JAXBElement<GetPokemonResponse> getPokemon(@RequestPayload JAXBElement<GetPokemonRequest> request) {
        GetPokemonRequest getPokemonRequest = request.getValue();
        PokemonApiResponse apiResponse = pokemonService.getPaginatedPokemon(getPokemonRequest.getOffset(), getPokemonRequest.getLimit());

        GetPokemonResponse response = new GetPokemonResponse();
        response.setCount(apiResponse.getCount());
        response.setNext(apiResponse.getNext());
        response.setPrevious(apiResponse.getPrevious());
        response.getResults().addAll(apiResponse.getResults());

        return new JAXBElement<>(new QName(NAMESPACE_URI, "getPokemonResponse"), GetPokemonResponse.class, response);
    }

}
