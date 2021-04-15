package com.haufe.beer.app.demo.repository;

import com.haufe.beer.app.demo.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<Provider> findProviderByIdAndCreatedBy(Long id, String name);
    List<Provider> findAllByCreatedBy(String name);
}
