package com.haufe.beer.app.demo.controller;

import com.haufe.beer.app.demo.exception.CustomException;
import com.haufe.beer.app.demo.exception.NotFoundException;
import com.haufe.beer.app.demo.model.dto.ProviderRequest;
import com.haufe.beer.app.demo.model.dto.ProviderResponse;
import com.haufe.beer.app.demo.service.ProviderService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping(path = "/provider")
    public ResponseEntity<?> addProvider(@RequestBody @Valid ProviderRequest request)  {
        log.info("addProvider() -> Adding new provider attempt");
        if (request == null) {
            log.error("addBeer() -> Unable to add new beer");
            throw new CustomException("Request parameter can't be null", HttpStatus.BAD_REQUEST);
        }
        ProviderResponse response = providerService.createProvider(ConversionUtils.providerRequestToEntity.apply(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProvider(@PathVariable Long id)  {
        log.info("getProvider() -> Get provider by id {} attempt", id);
        Optional<ProviderResponse> response = providerService.findProvider(id);
        if (!response.isPresent()) {
            log.error("Provider with id {}, doesn't exist", id);
            throw new NotFoundException("No provider found with given id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProviders()  {
        log.info("getAllProviders() -> Get all providers attempt");
        List<ProviderResponse> response = providerService.findAllProviders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody @Valid ProviderRequest request)  {
        log.info("updateProvider() -> Update providers attempt with given id {}", id);
        if (request == null) {
            log.error("updateProvider() -> Request body can't be null");
            throw new CustomException("Request body can't be null", HttpStatus.BAD_REQUEST);
        }
        ProviderResponse response = providerService.updateProvider(id, ConversionUtils.providerRequestToEntity.apply(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id)  {
        log.info("deleteProvider() -> Delete provider with id {}", id);
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }
}
