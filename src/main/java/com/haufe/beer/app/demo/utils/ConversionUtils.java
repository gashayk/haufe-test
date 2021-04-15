package com.haufe.beer.app.demo.utils;

import java.time.Instant;

public class ConversionUtils {


    private ConversionUtils(){}

    public static final ProviderRequestToEntity providerRequestToEntity = new ProviderRequestToEntity();
    public static final ProviderEntityToResponse providerEntityToResponse = new ProviderEntityToResponse();

    public static final BeerRequestToEntity beerRequestToEntity = new BeerRequestToEntity();
    public static final BeerEntityToResponse beerEntityToResponse = new BeerEntityToResponse();

    public static long timestamp() {
        Instant instant = Instant.now();
        return instant.getEpochSecond();
    }
}
