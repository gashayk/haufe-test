package com.haufe.beer.app.demo.service.impl;

import com.haufe.beer.app.demo.exception.NotFoundException;
import com.haufe.beer.app.demo.model.Roles;
import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.model.entity.Provider;
import com.haufe.beer.app.demo.repository.BeerRepository;
import com.haufe.beer.app.demo.repository.ProviderRepository;
import com.haufe.beer.app.demo.service.ProviderService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import com.haufe.beer.app.demo.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Override
    public ProviderResponse createProvider(String name, Provider provider) {
        provider.setCreatedBy(name);
        providerRepository.save(provider);
        return ConversionUtils.providerEntityToResponse.apply(provider);
    }

    @Override
    public Optional<ProviderResponse> findProvider(Long id, Principal principal) {
        Optional<Provider> provider;
        final Collection<GrantedAuthority> authorities = AppUtils.getGrantedAuthorities( principal);
        if (AppUtils.isAdmin(authorities)) {
            provider = providerRepository.findById(id);
        } else {
            provider = providerRepository.findProviderByIdAndCreatedBy(id, principal.getName());
        }

        if (provider.isPresent()) {
            Provider p = provider.get();
            final ProviderResponse response = ConversionUtils.providerEntityToResponse.apply(p);
            response.setBeers(beerRepository.findAllByProvider(p));
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
    public ProviderResponse updateProvider(Long id, Principal principal, Provider provider) {
        Optional<Provider> foundProvider = Optional.empty();
        final Collection<GrantedAuthority> authorities = AppUtils.getGrantedAuthorities( principal);
        if (AppUtils.isAdmin(authorities)) {
            foundProvider = providerRepository.findById(id);

        } else if (AppUtils.isManufacturer(authorities)) {
            foundProvider = providerRepository.findProviderByIdAndCreatedBy(id, provider.getName());
        }

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
