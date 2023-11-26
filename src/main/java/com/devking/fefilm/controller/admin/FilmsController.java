package com.devking.fefilm.controller.admin;

import com.devking.fefilm.model.Movie;
import com.devking.fefilm.service.CountryService;
import com.devking.fefilm.service.GenreService;
import com.devking.fefilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class FilmsController {
    @Autowired
    GenreService genreService;
    @Autowired
    CountryService countryService;
    @Autowired
    MovieService movieService;

    @GetMapping("/films")
    public ModelAndView getAllFilms(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/films");
        model.addAttribute("films",movieService.getAllMovie());
        return modelAndView;
    }
    //add film
    @GetMapping("/films/add")
    public ModelAndView addFilms(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/filmsAdd");
        model.addAttribute("newMovie",new Movie());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("countries", countryService.getAllCountry());
        return modelAndView;
    }
    @PostMapping("/films/add")
    public String addSelectFilm(@ModelAttribute("newMovie") Movie newMovie){

        movieService.updateMovie(newMovie);
        return "redirect:/admin/films";
    }
    //delete film
    @GetMapping("/films/delete/{id}")
    public String deleteFilm(@PathVariable int id){
        movieService.removeMovieById(id);
        return "redirect:/admin/films";
    }
    //update film
    @GetMapping("/films/update/{id}")
    public String updatePro(@PathVariable int id, Model model){
        Optional<Movie> tmpMovie = movieService.getMovieById(id);
        if (tmpMovie.isPresent()){
            model.addAttribute("newMovie", tmpMovie.get());
            model.addAttribute("genres", genreService.getAllGenre());
            model.addAttribute("countries", countryService.getAllCountry());
            return "admin/filmsAdd";
        }else {
            return "redirect:/admin/films";
        }
    }
}
