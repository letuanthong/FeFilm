package com.devking.fefilm.controller;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    public Country addNewCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
