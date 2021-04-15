package com.haufe.beer.app.demo.model.dto;

import com.haufe.beer.app.demo.model.entity.Beer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProviderResponse implements Serializable {

    private static final long serialVersionUID = -8187823985672706419L;

    private Long id;
    private String name;
    private String country;
    private LocalDateTime creationDate;
    private String createdBy;
    private List<Beer> beers;
}
