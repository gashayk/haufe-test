package com.haufe.beer.app.demo.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "statusCode","message","details"})
public class ErrorDetails {

    private int statusCode;
    private long timestamp;
    private String message;
    private String details;
}
