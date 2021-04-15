package com.haufe.beer.app.demo.utils;

import com.haufe.beer.app.demo.model.dto.ProviderRequest;
import com.haufe.beer.app.demo.model.entity.Provider;
import org.modelmapper.ModelMapper;

import java.util.function.Function;

public class ProviderRequestToEntity implements Function<ProviderRequest, Provider> {

    @Override
    public Provider apply(ProviderRequest input) {
        if (input == null) return null;

        ModelMapper mp = new ModelMapper();
        return mp.map(input, Provider.class);
    }
}
