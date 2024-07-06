package com.example.pokemon.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "pokemonApiResponse")
@XmlType(propOrder = {"count", "next", "previous", "results"})
public class PokemonApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<GetPokemonResponse.Results> results;

    @XmlElement
    public int getCount() {
        return count;
    }

    @XmlElement
    public String getNext() {
        return next;
    }

    @XmlElement
    public String getPrevious() {
        return previous;
    }

    @XmlElement
    public List<GetPokemonResponse.Results> getResults() {
        return results;
    }
}
