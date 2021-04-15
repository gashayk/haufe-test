package com.haufe.beer.app.demo.service.impl;

import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;
import com.haufe.beer.app.demo.repository.ProviderRepository;
import com.haufe.beer.app.demo.service.ProviderService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public ProviderResponse createProvider(Provider provider) {
        providerRepository.save(provider);
        return ConversionUtils.providerEntityToResponse.apply(provider);
    }

    @Override
    public Optional<ProviderResponse> findProvider(Long id) {
        final Optional<Provider> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            Provider p = provider.get();
            final ProviderResponse response = ConversionUtils.providerEntityToResponse.apply(p);
            return Optional.of(response);
        }
        return Optional.empty();
    }
}
