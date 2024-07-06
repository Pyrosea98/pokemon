package com.example.pokemon;

import com.example.pokemon.model.GetPokemonRequest;
import com.example.pokemon.model.GetPokemonResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PokemonIntegrationTest {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Test
    void testGetPokemonEndpoint() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);

        GetPokemonRequest request = new GetPokemonRequest();
        request.setOffset(0);
        request.setLimit(10);

        GetPokemonResponse response = (GetPokemonResponse) webServiceTemplate
                .marshalSendAndReceive("http://localhost:8080/ws", request);

        assertNotNull(response);
    }
}
