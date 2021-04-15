package com.haufe.beer.app.demo.repository;

import com.haufe.beer.app.demo.model.entity.Beer;
import com.haufe.beer.app.demo.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findAllByProvider(Provider provider);
}
