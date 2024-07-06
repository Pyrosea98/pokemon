package com.example.pokemon.model;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Setter
public class Pokemon {
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
