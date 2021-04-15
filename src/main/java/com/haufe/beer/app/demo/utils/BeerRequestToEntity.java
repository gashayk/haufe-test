package com.haufe.beer.app.demo.utils;

import com.haufe.beer.app.demo.model.dto.BeerRequest;
import com.haufe.beer.app.demo.model.entity.Beer;
import org.modelmapper.ModelMapper;

import java.util.function.Function;

public class BeerRequestToEntity implements Function<BeerRequest, Beer> {
    @Override
    public Beer apply(BeerRequest input) {
        if(input == null) return null;

        ModelMapper mp = new ModelMapper();
        return mp.map(input, Beer.class);
    }
}
