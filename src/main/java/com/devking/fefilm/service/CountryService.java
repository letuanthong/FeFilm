package com.devking.fefilm.service;

import com.devking.fefilm.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    public List<Country> getAllCountry();

    public void updateCountry(Country country);

    public void removeCountryById(int id);

    public Optional<Country> getCountryById(int id);
}
