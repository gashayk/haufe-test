package com.haufe.beer.app.demo.controller;

import com.haufe.beer.app.demo.model.dto.BeerRequest;
import com.haufe.beer.app.demo.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;


    @PostMapping(path = "/beer")
    public ResponseEntity<?> addBeer(@RequestBody @Valid BeerRequest request)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBeer(@PathVariable Long id)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllBeers()  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editBeer(@PathVariable Long id, @RequestBody @Valid BeerRequest request)  {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeBeer(@PathVariable Long id)  {
        return ResponseEntity.noContent().build();
    }
}
