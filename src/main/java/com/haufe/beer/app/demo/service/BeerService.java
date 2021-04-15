package com.haufe.beer.app.demo.service;

import com.haufe.beer.app.demo.model.dto.BeerResponse;
import com.haufe.beer.app.demo.model.entity.Beer;

import java.util.List;

public interface BeerService {

    BeerResponse createBeer(Beer beer);

    BeerResponse findBeer(Long id);

    List<BeerResponse> findAllBeers();

    BeerResponse editBeer(Long id, Beer beer);

    void deleteBeer(Long id);
}
