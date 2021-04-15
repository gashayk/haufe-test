package com.haufe.beer.app.demo.controller;

import com.haufe.beer.app.demo.exception.CustomException;
import com.haufe.beer.app.demo.model.dto.BeerRequest;
import com.haufe.beer.app.demo.model.dto.BeerResponse;
import com.haufe.beer.app.demo.service.BeerService;
import com.haufe.beer.app.demo.utils.ConversionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @PostMapping(path = "/beer")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANUFACTURER')")
    public ResponseEntity<?> addBeer(@RequestBody @Valid BeerRequest request, Principal principal)  {
        log.info("addBeer() -> Create new beer attempt");
        if (request == null) {
            log.error("addBeer() -> Unable to add new beer");
            throw new CustomException("Request parameter can't be null", HttpStatus.BAD_REQUEST);
        }
        BeerResponse response = beerService.createBeer(principal.getName(), ConversionUtils.beerRequestToEntity.apply(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANUFACTURER')")
    @PostMapping(path = "/{beerId}/provider/{providerId}")
    public ResponseEntity<?> assignBeerToProvider(@PathVariable(name = "beerId") Long bId,
                                                  @PathVariable(name = "providerId") Long pId)  {
        log.info("assignBeerToProvider() -> Assign beer to provider");
        beerService.assignBeerProvider(bId, pId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBeer(@PathVariable Long id)  {
        log.info("getBeer() -> Get beer with id {}", id);
        if (id == null) {
            log.error("getBeer() -> Unable to find beer with id {}, path variable can't be null", id);
            throw new CustomException("Path variable can't be null", HttpStatus.BAD_REQUEST);
        }

        BeerResponse response = beerService.findBeer(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllBeers()  {
        log.info("getAllBeers() -> Get all beers attempt");
        List<BeerResponse> response = beerService.findAllBeers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editBeer(@PathVariable Long id, @RequestBody @Valid BeerRequest request)  {
        if (request == null) {
            log.error("editBeer() -> Request body can't be null");
            throw new CustomException("Request body can't be null", HttpStatus.BAD_REQUEST);
        }

        BeerResponse response = beerService.editBeer(id, ConversionUtils.beerRequestToEntity.apply(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> removeBeer(@PathVariable Long id)  {
        log.info("removeBeer() -> Delete beer with id {}", id);
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }
}
