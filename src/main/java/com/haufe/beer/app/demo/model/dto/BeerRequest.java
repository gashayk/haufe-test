package com.haufe.beer.app.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BeerRequest implements Serializable {

    private static final long serialVersionUID = -5586588401553522923L;

    private String name;
    private String graduation;
    private String type;
    private String description;
}
