package com.haufe.beer.app.demo.service;

import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProviderService {

    ProviderResponse createProvider(String name, Provider provider);

    Optional<ProviderResponse> findProvider(Long id, Principal name);

    List<ProviderResponse> findAllProviders();

    ProviderResponse updateProvider(Long id, Principal principal, Provider provider);

    void deleteProvider(Long id);
}
