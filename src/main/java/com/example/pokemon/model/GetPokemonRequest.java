package com.example.pokemon.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getPokemonRequest", namespace = "http://example.com/pokemon")
public class GetPokemonRequest {

    @XmlElement(namespace = "http://example.com/pokemon", required = true)
    private int offset;

    @XmlElement(namespace = "http://example.com/pokemon", required = true)
    private int limit;
}
