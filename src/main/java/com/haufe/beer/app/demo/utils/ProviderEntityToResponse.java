package com.haufe.beer.app.demo.utils;

import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;
import org.modelmapper.ModelMapper;

import java.util.function.Function;

public class ProviderEntityToResponse implements Function<Provider, ProviderResponse> {

    @Override
    public ProviderResponse apply(Provider input) {
        if(input == null )return null;

        ModelMapper mp = new ModelMapper();
        return mp.map(input, ProviderResponse.class);
    }
}
