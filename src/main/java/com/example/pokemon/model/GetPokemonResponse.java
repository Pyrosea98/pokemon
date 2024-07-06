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
@XmlRootElement(name = "getPokemonResponse")
@XmlType(propOrder = {"count", "next", "previous", "results"})
public class GetPokemonResponse {
    private int count;
    private String next;
    private String previous;
    private List<Results> results = new ArrayList<>();

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
    public List<Results> getResults() {
        return results;
    }

    @Getter
    @Setter
    @XmlType(propOrder = {"name", "url"})
    public static class Results {
        private String name;
        private String url;

        @XmlElement
        public String getName() {
            return name;
        }

        @XmlElement
        public String getUrl() {
            return url;
        }
    }
}
