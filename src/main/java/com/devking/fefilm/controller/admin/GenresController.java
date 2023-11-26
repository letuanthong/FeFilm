package com.devking.fefilm.controller.admin;

import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.Genre;
import com.devking.fefilm.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class GenresController {
    @Autowired
    GenreService genreService;

    @GetMapping("/genres")
    public ModelAndView getAllGenres(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/genres");
        model.addAttribute("genres",genreService.getAllGenre());
        return modelAndView;
    }
    //add genres
    @GetMapping("/genres/add")
    public ModelAndView addGenres(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/genresAdd");
        model.addAttribute("genre", new Genre());
        return modelAndView;
    }

    @PostMapping("/genres/add")
    public String postGenAdd(@ModelAttribute("genre") Genre genre){
        genreService.updateGenre(genre);
        return "redirect:/admin/genres";
    }
    //delete genre
    @GetMapping("/genres/delete/{id}")
    public String deleteCou(@PathVariable int id){
        genreService.removeGenreById(id);
        return "redirect:/admin/genres";
    }
    //update country
    @GetMapping("/genres/update/{id}")
    public String updateCou(@PathVariable int id, Model model){
        Optional<Genre> genre = genreService.getGenreById(id);
        if(genre.isPresent()){
            model.addAttribute("genre", genre.get());
            return "/admin/genresAdd";
        }else {
            return "redirect:/admin/genres";
        }
    }
}
