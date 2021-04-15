package com.haufe.beer.app.demo.service.impl;

import com.haufe.beer.app.demo.exception.NotFoundException;
import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;
import com.haufe.beer.app.demo.repository.ProviderRepository;
import com.haufe.beer.app.demo.service.ProviderService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    public List<ProviderResponse> findAllProviders() {
        final List<Provider> providerList = providerRepository.findAll();
        if (providerList.isEmpty()) {
            return new ArrayList<>();
        }
        return providerList.stream().map(ConversionUtils.providerEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public ProviderResponse updateProvider(Long id, Provider provider) {
        final Optional<Provider> foundProvider = providerRepository.findById(id);
        if (!foundProvider.isPresent()) {
            log.error("No provider found with given id {}", id);
            throw new NotFoundException("No provider found with given id " + id, HttpStatus.NOT_FOUND);
        }
        Provider p = foundProvider.get();
        provider.setId(p.getId());
        provider.setCreationDate(p.getCreationDate());
        BeanUtils.copyProperties(provider, p);
        providerRepository.save(p);
        return ConversionUtils.providerEntityToResponse.apply(p);
    }

    @Override
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
