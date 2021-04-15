package com.haufe.beer.app.demo.utils;

import com.haufe.beer.app.demo.model.dto.BeerResponse;
import com.haufe.beer.app.demo.model.entity.Beer;
import org.modelmapper.ModelMapper;

import java.util.function.Function;

public class BeerEntityToResponse implements Function<Beer, BeerResponse> {

    @Override
    public BeerResponse apply(Beer input) {
        if(input == null) return null;

        ModelMapper mp = new ModelMapper();
        return mp.map(input, BeerResponse.class);
    }
}
