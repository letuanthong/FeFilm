package com.devking.fefilm.controller;

import com.devking.fefilm.model.*;
import com.devking.fefilm.model.request.MovieRequest;
import com.devking.fefilm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;

    @GetMapping
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
        List<Genre> genreList = genreService.getAllGenre();
        List<Country> countryList = countryService.getAllCountry();
        User currentUser = userService.getCurrentLoggingUser();

        model.addAttribute("carouselMovies", carouselMovies);
        model.addAttribute("popularMovies", popularMovies);
        model.addAttribute("chineseMovies", chineseMovies);
        model.addAttribute("koreaMovies", koreaMovies);
        model.addAttribute("japanMovies", japanMovies);
        model.addAttribute("hongKongMovies", hongKongMovies);
        model.addAttribute("genreList", genreList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("bookmark", new Bookmark());
        model.addAttribute("currentUser", currentUser);

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
