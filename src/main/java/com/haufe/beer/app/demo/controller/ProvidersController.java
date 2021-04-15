package com.haufe.beer.app.demo.controller;

import com.haufe.beer.app.demo.model.dto.ProviderRequest;
import com.haufe.beer.app.demo.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/providers")
public class ProvidersController {

    @Autowired
    private ProviderService providerService;

    @PostMapping(path = "/provider")
    public ResponseEntity<?> addProvider(@RequestBody @Valid ProviderRequest request)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProvider(@PathVariable Long id)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProviders()  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody @Valid ProviderRequest request)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id)  {
        return ResponseEntity.noContent().build();
    }
}
