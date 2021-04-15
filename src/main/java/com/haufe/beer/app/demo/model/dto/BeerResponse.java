package com.haufe.beer.app.demo.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BeerResponse implements Serializable {

    private static final long serialVersionUID = -1902525907606254135L;

    private Long id;
    private Long providerId;
    private String name;
    private String graduation;
    private String type;
    private String description;
    private LocalDateTime creationDate;
}
