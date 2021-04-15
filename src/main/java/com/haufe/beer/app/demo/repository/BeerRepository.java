package com.haufe.beer.app.demo.repository;

import com.haufe.beer.app.demo.model.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
