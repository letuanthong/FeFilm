package com.devking.fefilm.controller;

import com.devking.fefilm.model.Movie;
import com.devking.fefilm.model.request.MovieRequest;
import com.devking.fefilm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @Autowired
    private MovieService movieService;
    @GetMapping("")
    public ModelAndView index(Model model) {
        MovieRequest carouselMovieRequest = new MovieRequest("", "", "desc", 0, 3, "releaseYear");
        MovieRequest popularMovieRequest = new MovieRequest("", "", "desc", 0, 5, "imdb");
        MovieRequest chineseMovieRequest = new MovieRequest("country", "China", "desc", 0, 5, "imdb");
        MovieRequest koreaMovieRequest = new MovieRequest("country", "South Korea", "desc", 0, 5, "imdb");
        MovieRequest japanMovieRequest = new MovieRequest("country", "Japan", "desc", 0, 5, "imdb");
        MovieRequest hongkongMovieRequest = new MovieRequest("country", "HongKong", "desc", 0, 5, "imdb");

        Page<Movie> carouselMovies = movieService.getAllMoviesWithPagination(carouselMovieRequest);
        Page<Movie> popularMovies = movieService.getAllMoviesWithPagination(popularMovieRequest);
        Page<Movie> chineseMovies = movieService.getAllMoviesWithPagination(chineseMovieRequest);
        Page<Movie> koreaMovies = movieService.getAllMoviesWithPagination(koreaMovieRequest);
        Page<Movie> japanMovies = movieService.getAllMoviesWithPagination(japanMovieRequest);
        Page<Movie> hongKongMovies = movieService.getAllMoviesWithPagination(hongkongMovieRequest);

        model.addAttribute("carouselMovies", carouselMovies);
        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("chineseMovies", chineseMovies);
        model.addAttribute("koreaMovies", koreaMovies);
        model.addAttribute("japanMovies", japanMovies);
        model.addAttribute("hongKongMovies", hongKongMovies);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
    @GetMapping("/forgot")
    public ModelAndView forgot() {
        ModelAndView modelAndView = new ModelAndView("forgot");
        return modelAndView;
    }

}
