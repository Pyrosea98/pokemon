package com.example.pokemon;

import com.example.pokemon.model.GetPokemonRequest;
import com.example.pokemon.model.GetPokemonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@SpringBootTest
class PokemonIntegrationTest {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate webServiceTemplate;
    private MockWebServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        webServiceTemplate = new WebServiceTemplate(marshaller);
        mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    void testGetPokemonEndpoint() {
        GetPokemonRequest request = new GetPokemonRequest();
        request.setOffset(0);
        request.setLimit(10);

        JAXBElement<GetPokemonRequest> requestElement = new JAXBElement<>(
                new QName("http://example.com/pokemon", "getPokemonRequest"),
                GetPokemonRequest.class,
                request
        );

        Source responsePayload = new StringSource(
                "<ns2:getPokemonResponse xmlns:ns2=\"http://example.com/pokemon\">" +
                        "<ns2:count>1118</ns2:count>" +
                        "<ns2:next>https://pokeapi.co/api/v2/pokemon?offset=10&amp;limit=10</ns2:next>" +
                        "<ns2:previous>null</ns2:previous>" +
                        "<ns2:results><ns2:name>bulbasaur</ns2:name><ns2:url>https://pokeapi.co/api/v2/pokemon/1/</ns2:url></ns2:results>" +
                        "<ns2:results><ns2:name>ivysaur</ns2:name><ns2:url>https://pokeapi.co/api/v2/pokemon/2/</ns2:url></ns2:results>" +
                        "</ns2:getPokemonResponse>");

        mockServer.expect(payload(new StringSource(
                        "<ns2:getPokemonRequest xmlns:ns2=\"http://example.com/pokemon\">" +
                                "<ns2:offset>0</ns2:offset><ns2:limit>10</ns2:limit></ns2:getPokemonRequest>")))
                .andRespond(withPayload(responsePayload));

        GetPokemonResponse response = (GetPokemonResponse) webServiceTemplate
                .marshalSendAndReceive("http://localhost:8080/ws", requestElement);

        assertNotNull(response);
        assertEquals(1118, response.getCount());
        assertEquals("https://pokeapi.co/api/v2/pokemon?offset=10&limit=10", response.getNext());
        assertNotNull(response.getResults());
        assertEquals(2, response.getResults().size());
        assertEquals("bulbasaur", response.getResults().get(0).getName());
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", response.getResults().get(0).getUrl());
    }
}
