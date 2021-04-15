package com.haufe.beer.app.demo.service.impl;

import com.haufe.beer.app.demo.exception.NotFoundException;
import com.haufe.beer.app.demo.model.dto.BeerResponse;
import com.haufe.beer.app.demo.model.entity.Beer;
import com.haufe.beer.app.demo.model.entity.Provider;
import com.haufe.beer.app.demo.repository.BeerRepository;
import com.haufe.beer.app.demo.repository.ProviderRepository;
import com.haufe.beer.app.demo.service.BeerService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public BeerResponse createBeer(String name, Beer beer) {
        beer.setCreatedBy(name);
        beerRepository.save(beer);
        return ConversionUtils.beerEntityToResponse.apply(beer);
    }

    @Override
    public BeerResponse findBeer(Long id) {
        final Optional<Beer> foundBeer = beerRepository.findById(id);
        if (!foundBeer.isPresent()) {
            log.error("findBeer() -> No beer found with given id {}", id);
            throw new NotFoundException("No beer found with given id", HttpStatus.NOT_FOUND);
        }
        return ConversionUtils.beerEntityToResponse.apply(foundBeer.get());
    }

    @Override
    public List<BeerResponse> findAllBeers() {
        final List<Beer> beerList = beerRepository.findAll();
        if (beerList.isEmpty()) {
            log.error("findAllBeers() -> No beer found at all");
            throw new NotFoundException("Sorry, no beer found at all", HttpStatus.NOT_FOUND);
        }
        return beerList.stream().map(ConversionUtils.beerEntityToResponse).collect(Collectors.toList());
    }

    @Override
    public BeerResponse editBeer(Long id, Beer beer) {
        final Optional<Beer> foundBeer = beerRepository.findById(id);
        if (!foundBeer.isPresent()) {
            log.error("editBeer() -> No beer found with given id {}", id);
            throw new NotFoundException("No beer found with given id", HttpStatus.NOT_FOUND);
        }
        Beer b = foundBeer.get();
        beer.setId(b.getId());
        beer.setCreationDate(b.getCreationDate());
        beer.setModificationDate(LocalDateTime.now());
        BeanUtils.copyProperties(beer, b);
        beerRepository.save(b);
        return ConversionUtils.beerEntityToResponse.apply(b);
    }

    @Override
    public void deleteBeer(Long id) {
        beerRepository.deleteById(id);
    }

    @Override
    public void assignBeerProvider(Long bId, Long pId) {
        final Optional<Beer> foundBeer = beerRepository.findById(bId);
        if (!foundBeer.isPresent()) {
            log.error("assignBeerProvider() -> No beer found with given id {}", bId);
            throw new NotFoundException("No beer found with given id", HttpStatus.NOT_FOUND);
        }
        final Optional<Provider> foundProvider = providerRepository.findById(pId);
        if (!foundProvider.isPresent()) {
            log.error("assignBeerProvider() -> No provider found with given id {}", pId);
            throw new NotFoundException("No provider found with given id", HttpStatus.NOT_FOUND);
        }
        foundBeer.get().setProvider(foundProvider.get());
        beerRepository.save(foundBeer.get());
    }
}
