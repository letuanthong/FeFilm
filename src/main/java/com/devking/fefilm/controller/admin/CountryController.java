package com.devking.fefilm.controller.admin;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Genre;
import com.devking.fefilm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CountryController {
    @Autowired
    CountryService countryService;
    @GetMapping("/countries")
    public ModelAndView getAllCountry(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/countries");
        model.addAttribute("countries",countryService.getAllCountry());
        return modelAndView;
    }
    //add country
    @GetMapping("/countries/add")
    public ModelAndView addCountry(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/countriesAdd");
        model.addAttribute("countries",new Country());
        return modelAndView;
    }

    @PostMapping("/countries/add")
    public String postCountry(@ModelAttribute("genres") Country country){
        countryService.updateCountry(country);
        return "redirect:/admin/countries";
    }
    //delete country
    @GetMapping("/countries/delete/{id}")
    public String deleteCou(@PathVariable int id){
        countryService.removeCountryById(id);
        return "redirect:/admin/countries";
    }
    //update country
    @GetMapping("/countries/update/{id}")
    public String updateCou(@PathVariable int id, Model model){
        Optional<Country> country = countryService.getCountryById(id);
        if(country.isPresent()){
            model.addAttribute("country", country.get());
            return "/admin/countriesAdd";
        }else {
            return "redirect:/admin/countries";
        }
    }
}
