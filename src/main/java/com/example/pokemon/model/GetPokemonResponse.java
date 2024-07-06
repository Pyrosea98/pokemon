package com.example.pokemon.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "getPokemonResponse", namespace = "http://example.com/pokemon")
@XmlType(propOrder = {"count", "next", "previous", "results"})
public class GetPokemonResponse {
    private int count;
    private String next;
    private String previous;
    private List<Results> results = new ArrayList<>();

    @XmlElement(namespace = "http://example.com/pokemon")
    public int getCount() {
        return count;
    }

    @XmlElement(namespace = "http://example.com/pokemon")
    public String getNext() {
        return next;
    }

    @XmlElement(namespace = "http://example.com/pokemon")
    public String getPrevious() {
        return previous;
    }

    @XmlElement(namespace = "http://example.com/pokemon")
    public List<Results> getResults() {
        return results;
    }

    @Getter
    @Setter
    @XmlType(propOrder = {"name", "url"})
    public static class Results {
        private String name;
        private String url;

        @XmlElement(namespace = "http://example.com/pokemon")
        public String getName() {
            return name;
        }

        @XmlElement(namespace = "http://example.com/pokemon")
        public String getUrl() {
            return url;
        }
    }
}
