package com.devking.fefilm.controller.admin;

import com.devking.fefilm.service.CountryService;
import com.devking.fefilm.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class FilmsController {
    @Autowired
    GenreService genreService;
    @Autowired
    CountryService countryService;


    @GetMapping("/films")
    public ModelAndView getAllFilms() {
        ModelAndView modelAndView = new ModelAndView("/admin/films");
        return modelAndView;
    }
    //add film
    @GetMapping("/films/add")
    public ModelAndView addFilms(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/filmsAdd");
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("countries", countryService.getAllCountry());
        return modelAndView;
    }
}
