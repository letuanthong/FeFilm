package com.devking.fefilm.service.impl;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.repository.CountryRepository;
import com.devking.fefilm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;
    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void removeCountryById(int id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> getCountryById(int id) {
        return countryRepository.findById(id);
    }
}
