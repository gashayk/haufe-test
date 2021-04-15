package com.haufe.beer.app.demo.service;

import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    ProviderResponse createProvider(Provider provider);

    Optional<ProviderResponse> findProvider(Long id);

    List<ProviderResponse> findAllProviders();

    ProviderResponse updateProvider(Long id, Provider provider);

    void deleteProvider(Long id);
}
