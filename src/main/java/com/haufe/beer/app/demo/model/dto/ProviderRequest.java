package com.haufe.beer.app.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ProviderRequest implements Serializable {

    private static final long serialVersionUID = -5064314815989687146L;

    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Country is mandatory")
    private String country;
}
